package ci.gstoreplus.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.entity.shared.Personne;
import ci.gstoreplus.metier.admin.PersonneMetier;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class AdminController {

	@Autowired
	PersonneMetier personneMetier;
	@Autowired
	private ObjectMapper jsonMapper;
	// recherche les personne par id
		@GetMapping("/admin/{id}")
		public String getAdminById(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Personne> reponse;

			try {

				Personne p = personneMetier.findById(id);
				List<String> messages = new ArrayList<>();
				messages.add(String.format(" à été créer avec succes"));
				reponse = new Reponse<Personne>(0, messages, p);

			} catch (Exception e) {

				reponse = new Reponse<Personne>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
		}

}
