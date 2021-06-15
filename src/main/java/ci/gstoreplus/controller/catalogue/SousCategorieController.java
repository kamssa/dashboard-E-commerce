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

import ci.gstoreplus.entity.catalogue.Categories;
import ci.gstoreplus.entity.catalogue.SousCategories;
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metier.catalogue.ICategorieMetier;
import ci.gstoreplus.metier.catalogue.ISousCategorieMetier;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SousCategorieController {
	@Autowired
	private ISousCategorieMetier sousCategorieMetier;

	@Autowired
	private ObjectMapper jsonMapper;

// recuper categorie par identifiant
	private Reponse<SousCategories> getSousCategoriesById(Long id) {
		SousCategories sc = null;

		try {
			sc = sousCategorieMetier.findById(id);
			if (sc == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("La sous catégorie n'existe pas", id));
				new Reponse<Categories>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<Categories>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<SousCategories>(0, null, sc);
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////// enregistrer une categories  dans la base de donnee
////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/sousCategories")
	public String creer(@RequestBody SousCategories sc) throws JsonProcessingException {
		Reponse<SousCategories> reponse;
		System.out.println(sc);
		try {

			SousCategories scat = sousCategorieMetier.creer(sc);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", scat.getNom()));
			reponse = new Reponse<SousCategories>(0, messages, scat);

		} catch (InvalideGstoreException e) {

			reponse = new Reponse<SousCategories>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	@PutMapping("/sousCategories")
	public String update(@RequestBody SousCategories modif) throws JsonProcessingException {

		Reponse<SousCategories> reponse = null;
		Reponse<SousCategories> reponsePersModif = null;
		// on recupere abonnement a modifier
		System.out.println("modif recupere1:" + modif);
		reponsePersModif = getSousCategoriesById(modif.getId());
		if (reponsePersModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				SousCategories sc = sousCategorieMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", sc.getId()));
				reponse = new Reponse<SousCategories>(0, messages, sc);
			} catch (InvalideGstoreException e) {

				reponse = new Reponse<SousCategories>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("La sous catégories n'existe pas"));
			reponse = new Reponse<SousCategories>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

    ////////recuperer une categorie  par son id
	@GetMapping("/sousCategories/{id}")
	public String getById(@PathVariable Long id) throws JsonProcessingException {
		// Annotation @PathVariable permet de recuperer le paremettre dans URI
		Reponse<SousCategories> reponse = null;

		reponse = getSousCategoriesById(id);
		if (reponse.getBody() == null) {
			throw new RuntimeException("pas d'enregistrement pour cette sous catégorie");
		}

		return jsonMapper.writeValueAsString(reponse);

	}

        //get all categories
	@GetMapping("/sousCategories")
	public String findAll() throws JsonProcessingException {
		Reponse<List<SousCategories>> reponse;
		try {
			List<SousCategories> sc = sousCategorieMetier.findAll();
			if (!sc.isEmpty()) {
				reponse = new Reponse<List<SousCategories>>(0, null, sc);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas d'abonnés enregistrées");
				reponse = new Reponse<List<SousCategories>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
	// supprimer une categorie
		@DeleteMapping("/sousCategories/{id}")
		public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Boolean> reponse = null;

			try {

				reponse = new Reponse<Boolean>(0, null, sousCategorieMetier.supprimer(id));

			} catch (RuntimeException e1) {
				reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
			}

			return jsonMapper.writeValueAsString(reponse);
		}
}
