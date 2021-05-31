package ci.gstoreplus.controller.catalogue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.entity.catalogue.Produits;
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metier.catalogue.IProduitMetier;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProduitController {
	@Autowired
	private IProduitMetier produitMetier;

	@Autowired
	private ObjectMapper jsonMapper;

// recuper categorie par identifiant
	private Reponse<Produits> getProduitById(Long id) {
		Produits produits = null;

		try {
			produits = produitMetier.findById(id);
			if (produits == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("Le produit n'existe pas", id));
				new Reponse<Produits>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<Produits>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<Produits>(0, null, produits);
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////// enregistrer une categories  dans la base de donnee
////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/produit")
	public String creer(@RequestBody Produits produits) throws JsonProcessingException {
		Reponse<Produits> reponse;
		System.out.println(produits);
		try {

			Produits prod = produitMetier.creer(produits);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", prod.getNom()));
			reponse = new Reponse<Produits>(0, messages, prod);

		} catch (InvalideGstoreException e) {

			reponse = new Reponse<Produits>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	@PutMapping("/produit")
	public String update(@RequestBody Produits modif) throws JsonProcessingException {

		Reponse<Produits> reponse = null;
		Reponse<Produits> reponsePersModif = null;
		// on recupere abonnement a modifier
		System.out.println("modif recupere1:" + modif);
		reponsePersModif = getProduitById(modif.getId());
		if (reponsePersModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				Produits produit = produitMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", produit.getId()));
				reponse = new Reponse<Produits>(0, messages, produit);
			} catch (InvalideGstoreException e) {

				reponse = new Reponse<Produits>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("La catégories n'existe pas"));
			reponse = new Reponse<Produits>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

    ////////recuperer une categorie  par son id
	@GetMapping("/produit/{id}")
	public String getById(@PathVariable Long id) throws JsonProcessingException {
		// Annotation @PathVariable permet de recuperer le paremettre dans URI
		Reponse<Produits> reponse = null;

		reponse = getProduitById(id);
		if (reponse.getBody() == null) {
			throw new RuntimeException("pas d'enregistrement pour ce produit");
		}

		return jsonMapper.writeValueAsString(reponse);

	}

        //get all categories
	@GetMapping("/produit")
	public String findAll() throws JsonProcessingException {
		Reponse<List<Produits>> reponse;
		try {
			List<Produits> produits = produitMetier.findAll();
			if (!produits.isEmpty()) {
				reponse = new Reponse<List<Produits>>(0, null, produits);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas d'abonnés enregistrées");
				reponse = new Reponse<List<Produits>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
	// supprimer une categorie
		@DeleteMapping("/produit/{id}")
		public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Boolean> reponse = null;

			try {

				reponse = new Reponse<Boolean>(0, null, produitMetier.supprimer(id));

			} catch (RuntimeException e1) {
				reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
			}

			return jsonMapper.writeValueAsString(reponse);
		}
}
