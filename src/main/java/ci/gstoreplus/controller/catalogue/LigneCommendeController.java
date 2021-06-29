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

import ci.gstoreplus.entity.commande.LigneDeCommande;
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metier.commande.ILigneCommandeMetier;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;



@RestController
@RequestMapping("/api")
@CrossOrigin
public class LigneCommendeController {
	@Autowired
	private ILigneCommandeMetier ligneCommandeMetier;
	@Autowired
	private ObjectMapper jsonMapper;
	
	
	@PostMapping("/ligneCommande")
	public String creer(@RequestBody LigneDeCommande entite) throws JsonProcessingException {
		Reponse<LigneDeCommande> reponse;
		try {
			LigneDeCommande l1 = ligneCommandeMetier.creer(entite);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s à été créer avec succes", l1.getId()));
			reponse = new Reponse<LigneDeCommande>(0, messages, l1);

		} catch (InvalideGstoreException e) {

			reponse = new Reponse<LigneDeCommande>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}
	 @DeleteMapping("/ligneCommande/{id}")
		public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Boolean> reponse = null;

			try {

				reponse = new Reponse<Boolean>(0, null, ligneCommandeMetier.supprimer(id));

			} catch (RuntimeException e1) {
				reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
			}

			return jsonMapper.writeValueAsString(reponse);
		}

}
