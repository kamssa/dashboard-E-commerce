package ci.gstoreplus.controller.catalogue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.metier.catalogue.ICategorieMetier;
import ci.gstoreplus.metier.catalogue.ImageMetier;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ImageController {
	
	@Autowired
	private ImageMetier imageMetier;

	@Autowired
	private ObjectMapper jsonMapper;
	// supprimer une categorie
			@DeleteMapping("/image/{id}")
			public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

				Reponse<Boolean> reponse = null;

				try {

					reponse = new Reponse<Boolean>(0, null, imageMetier.supprimer(id));

				} catch (RuntimeException e1) {
					reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
				}

				return jsonMapper.writeValueAsString(reponse);
			}
}
