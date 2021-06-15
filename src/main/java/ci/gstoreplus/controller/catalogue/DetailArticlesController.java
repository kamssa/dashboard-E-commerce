package ci.gstoreplus.controller.catalogue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.entity.catalogue.DetailArticles;
import ci.gstoreplus.entity.catalogue.ImageDetail;
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metier.catalogue.CloudinaryService;
import ci.gstoreplus.metier.catalogue.IDetailArticlesMetier;
import ci.gstoreplus.metier.catalogue.ImageDetailArticleMetier;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class DetailArticlesController {
	
	@Autowired
	private IDetailArticlesMetier detailArticlesMetier;
	@Autowired
	private ImageDetailArticleMetier imageDetailArticleMetier;

	@Autowired
	private ObjectMapper jsonMapper;
	@Autowired
	CloudinaryService cloudinaryService;
	


// recuper categorie par identifiant
	private Reponse<DetailArticles> getDetailArticleById(Long id) {
		DetailArticles detailArticles = null;

		try {
			detailArticles = detailArticlesMetier.findById(id);
			if (detailArticles == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("Le detailArticles n'existe pas", id));
				new Reponse<DetailArticles>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<DetailArticles>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<DetailArticles>(0, null, detailArticles);
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////// enregistrer une categories  dans la base de donnee
////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/detailArticles")
	public String creer(@RequestBody DetailArticles detailArticles) throws JsonProcessingException {
		Reponse<DetailArticles> reponse;
		System.out.println(detailArticles);
		try {

			DetailArticles detailArticle = detailArticlesMetier.creer(detailArticles);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", detailArticle.getId()));
			reponse = new Reponse<DetailArticles>(0, messages, detailArticle);

		} catch (InvalideGstoreException e) {

			reponse = new Reponse<DetailArticles>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	@PutMapping("/detailArticles")
	public String update(@RequestBody DetailArticles modif) throws JsonProcessingException {

		Reponse<DetailArticles> reponse = null;
		Reponse<DetailArticles> reponsePersModif = null;
		// on recupere abonnement a modifier
		System.out.println("modif recupere1:" + modif);
		reponsePersModif = getDetailArticleById(modif.getId());
		if (reponsePersModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				DetailArticles detailArticles = detailArticlesMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", detailArticles.getId()));
				reponse = new Reponse<DetailArticles>(0, messages, detailArticles);
			} catch (InvalideGstoreException e) {

				reponse = new Reponse<DetailArticles>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format(" detailArticles n'existe pas"));
			reponse = new Reponse<DetailArticles>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

    ////////recuperer une categorie  par son id
	@GetMapping("/detailArticles/{id}")
	public String getById(@PathVariable Long id) throws JsonProcessingException {
		// Annotation @PathVariable permet de recuperer le paremettre dans URI
		Reponse<DetailArticles> reponse = null;

		reponse = getDetailArticleById(id);
		if (reponse.getBody() == null) {
			throw new RuntimeException("pas d'enregistrement pour ce detailArticles");
		}

		return jsonMapper.writeValueAsString(reponse);

	}
	@GetMapping("/detailArticleByIdArticle/{id}")
	public String getByIdDetailArticle(@PathVariable Long id) throws JsonProcessingException {
		Reponse<DetailArticles> reponse;
		try {
			DetailArticles detailArticles = detailArticlesMetier.findDetailArticleByIdArticle(id);
			
		reponse = new Reponse<DetailArticles>(0, null, detailArticles);
			
     } catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

        //get all categories
	@GetMapping("/detailArticles")
	public String findAll() throws JsonProcessingException {
		Reponse<List<DetailArticles>> reponse;
		try {
			List<DetailArticles> detailArticles = detailArticlesMetier.findAll();
			if (!detailArticles.isEmpty()) {
				reponse = new Reponse<List<DetailArticles>>(0, null, detailArticles);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas d'abonnés enregistrées");
				reponse = new Reponse<List<DetailArticles>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
	// supprimer une categorie
		@DeleteMapping("/detailArticles/{id}")
		public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Boolean> reponse = null;

			try {

				reponse = new Reponse<Boolean>(0, null, detailArticlesMetier.supprimer(id));

			} catch (RuntimeException e1) {
				reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
			}

			return jsonMapper.writeValueAsString(reponse);
		}

		@GetMapping("/image/{idDetailArticles}")
		public String getImageByIdDetailArticle(@PathVariable("idDetailArticles") long idDetailArticles)
				throws JsonProcessingException, InvalideGstoreException {
			Reponse<List<ImageDetail>> reponse;
			try {
				List<ImageDetail> photos = imageDetailArticleMetier.findImageByIdDetailArticles(idDetailArticles);
				if (!photos.isEmpty()) {
					reponse = new Reponse<List<ImageDetail>>(0, null, photos);
				} else {
					List<String> messages = new ArrayList<>();
					messages.add("Pas de photos enregistrées");
					reponse = new Reponse<List<ImageDetail>>(1, messages, new ArrayList<>());
				}

			} catch (Exception e) {
				reponse = new Reponse<List<ImageDetail>>(1, Static.getErreursForException(e), new ArrayList<>());
			}
			return jsonMapper.writeValueAsString(reponse);

		}
		
}
