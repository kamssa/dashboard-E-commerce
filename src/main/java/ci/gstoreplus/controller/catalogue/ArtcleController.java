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
import ci.gstoreplus.entity.catalogue.Articles;
import ci.gstoreplus.entity.catalogue.Image;
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metier.catalogue.CloudinaryService;
import ci.gstoreplus.metier.catalogue.IArticleMetier;
import ci.gstoreplus.metier.catalogue.ImageMetier;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ArtcleController {
	@Autowired
	private IArticleMetier articleMetier;
	@Autowired
	private ImageMetier imageMetier;
	@Autowired
	private ObjectMapper jsonMapper;
	@Autowired
	CloudinaryService cloudinaryService;

// recuper categorie par identifiant
	private Reponse<Articles> getArticlesById(Long id) {
		Articles article = null;

		try {
			article = articleMetier.findById(id);
			if (article == null) {
				List<String> messages = new ArrayList<>();
				messages.add(String.format("L'articles n'existe pas", id));
				new Reponse<Articles>(2, messages, null);

			}
		} catch (RuntimeException e) {
			new Reponse<Articles>(1, Static.getErreursForException(e), null);
		}

		return new Reponse<Articles>(0, null, article);
	}

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////// enregistrer une categories  dans la base de donnee
////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

	@PostMapping("/article")
	public String creer(@RequestBody Articles article) throws JsonProcessingException {
		Reponse<Articles> reponse;
		System.out.println(article);
		try {

			Articles cat = articleMetier.creer(article);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", cat.getNom()));
			reponse = new Reponse<Articles>(0, messages, cat);

		} catch (InvalideGstoreException e) {

			reponse = new Reponse<Articles>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}

	@PutMapping("/article")
	public String update(@RequestBody Articles modif) throws JsonProcessingException {

		Reponse<Articles> reponse = null;
		Reponse<Articles> reponsePersModif = null;
		// on recupere abonnement a modifier
		System.out.println("modif recupere1:" + modif);
		reponsePersModif = getArticlesById(modif.getId());
		if (reponsePersModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:" + modif);
				Articles article = articleMetier.modifier(modif);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", article.getId()));
				reponse = new Reponse<Articles>(0, messages, article);
			} catch (InvalideGstoreException e) {

				reponse = new Reponse<Articles>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("La catégories n'existe pas"));
			reponse = new Reponse<Articles>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}

	//////// recuperer une categorie par son id
	@GetMapping("/article/{id}")
	public String getById(@PathVariable Long id) throws JsonProcessingException {
		// Annotation @PathVariable permet de recuperer le paremettre dans URI
		Reponse<Articles> reponse = null;

		reponse = getArticlesById(id);
		if (reponse.getBody() == null) {
			throw new RuntimeException("pas d'enregistrement pour cette catégorie");
		}

		return jsonMapper.writeValueAsString(reponse);

	}

	// get all categories
	@GetMapping("/article")
	public String findAll() throws JsonProcessingException {
		Reponse<List<Articles>> reponse;
		try {
			List<Articles> articles = articleMetier.findAll();
			if (!articles.isEmpty()) {
				reponse = new Reponse<List<Articles>>(0, null, articles);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas d'abonnés enregistrées");
				reponse = new Reponse<List<Articles>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}

	// supprimer une categorie
	@DeleteMapping("/article/{id}")
	public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Boolean> reponse = null;

		try {

			reponse = new Reponse<Boolean>(0, null, articleMetier.supprimer(id));

		} catch (RuntimeException e1) {
			reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
		}

		return jsonMapper.writeValueAsString(reponse);
	}

////////////get photo par id d'une travaux
	@GetMapping("/image/{idArticles}")
	public String getPhotoByIdArticle(@PathVariable("idArticles") long idArticles)
			throws JsonProcessingException, InvalideGstoreException {
		Reponse<List<Image>> reponse;
		try {
			List<Image> photos = imageMetier.findByIdArticles(idArticles);
			if (!photos.isEmpty()) {
				reponse = new Reponse<List<Image>>(0, null, photos);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas de photos enregistrées");
				reponse = new Reponse<List<Image>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<List<Image>>(1, Static.getErreursForException(e), new ArrayList<>());
		}
		return jsonMapper.writeValueAsString(reponse);

	}
	// solution alterntive cloudinary//////////////////////////
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile,
			@RequestParam Long id) throws IOException, InvalideGstoreException{
		Map result = cloudinaryService.upload(multipartFile);
		Image image = new Image();
		image.setIdArticles(id);
		image.setPath((String) result.get("url"));
		imageMetier.creer(image);
		
		//imageMetier.modifier(img);
		return new ResponseEntity(result, HttpStatus.OK);
	}
}
