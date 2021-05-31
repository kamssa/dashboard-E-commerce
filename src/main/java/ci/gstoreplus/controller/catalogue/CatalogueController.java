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
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metier.catalogue.ICategorieMetier;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CatalogueController {
	@Autowired
	private ICategorieMetier categorieMetier;

	@Autowired
	private ObjectMapper jsonMapper;

// recuper categorie par identifiant
	private Reponse<Categories> getCategoriesById(Long id) {
		Categories categories = null;

		try {
			categories = categorieMetier.findById(id);
			if (categories == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("La catégorie n'existe pas", id));
				new Reponse<Categories>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<Categories>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<Categories>(0, null, categories);
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////// enregistrer une categories  dans la base de donnee
////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/categories")
	public String creer(@RequestBody Categories categories) throws JsonProcessingException {
		Reponse<Categories> reponse;
		System.out.println(categories);
		try {

			Categories cat = categorieMetier.creer(categories);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", cat.getNom()));
			reponse = new Reponse<Categories>(0, messages, cat);

		} catch (InvalideGstoreException e) {

			reponse = new Reponse<Categories>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	@PutMapping("/categories")
	public String update(@RequestBody Categories modif) throws JsonProcessingException {

		Reponse<Categories> reponse = null;
		Reponse<Categories> reponsePersModif = null;
		// on recupere abonnement a modifier
		System.out.println("modif recupere1:" + modif);
		reponsePersModif = getCategoriesById(modif.getId());
		if (reponsePersModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				Categories categories = categorieMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", categories.getId()));
				reponse = new Reponse<Categories>(0, messages, categories);
			} catch (InvalideGstoreException e) {

				reponse = new Reponse<Categories>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("La catégories n'existe pas"));
			reponse = new Reponse<Categories>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

    ////////recuperer une categorie  par son id
	@GetMapping("/categories/{id}")
	public String getById(@PathVariable Long id) throws JsonProcessingException {
		// Annotation @PathVariable permet de recuperer le paremettre dans URI
		Reponse<Categories> reponse = null;

		reponse = getCategoriesById(id);
		if (reponse.getBody() == null) {
			throw new RuntimeException("pas d'enregistrement pour cette catégorie");
		}

		return jsonMapper.writeValueAsString(reponse);

	}
////////recuperer une categorie  par son id
@GetMapping("/getCategoriesByNom/{id}")
public String getByNom(@PathVariable String nom) throws JsonProcessingException {
	Reponse<Categories> reponse;

	try {

		Categories c = categorieMetier.findByNom(nom);
		List<String> messages = new ArrayList<>();
		messages.add(String.format(" à été recuperer avec succes"));
		reponse = new Reponse<Categories>(0, messages, c);

	} catch (Exception e) {

		reponse = new Reponse<Categories>(1, Static.getErreursForException(e), null);
	}
	return jsonMapper.writeValueAsString(reponse);

}

        //get all categories
	@GetMapping("/categories")
	public String findAll() throws JsonProcessingException {
		Reponse<List<Categories>> reponse;
		try {
			List<Categories> categories = categorieMetier.findAll();
			if (!categories.isEmpty()) {
				reponse = new Reponse<List<Categories>>(0, null, categories);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas d'abonnés enregistrées");
				reponse = new Reponse<List<Categories>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
	// supprimer une categorie
		@DeleteMapping("/categories/{id}")
		public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Boolean> reponse = null;

			try {

				reponse = new Reponse<Boolean>(0, null, categorieMetier.supprimer(id));

			} catch (RuntimeException e1) {
				reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
			}

			return jsonMapper.writeValueAsString(reponse);
		}
}
