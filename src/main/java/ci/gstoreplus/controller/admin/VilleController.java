package ci.gstoreplus.controller.admin;

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


import ci.gstoreplus.entity.client.Ville;
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metier.admin.IVilleMetier;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class VilleController {
	
    @Autowired
	private IVilleMetier villeMetier;
    @Autowired
	private ObjectMapper jsonMapper;
 // recuper Departement par identifiant
    
    
 	private Reponse<Ville> getVilleById(Long id) {
 		Ville ville = null;

 		try {
 			ville = villeMetier.findById(id);
 			if (ville == null) {
 				List<String> messages = new ArrayList<>();
 				messages.add(String.format("ville n'existe pas", id));
 				new Reponse<Ville>(2, messages, null);

 			}
 		} catch (RuntimeException e) {
 			new Reponse<Ville>(1, Static.getErreursForException(e), null);
 		}

 		return new Reponse<Ville>(0, null, ville);
 	}

 //////////////////////////////////////////////////////////////////////////////////////////////
 ////////////////// enregistrer un departement  dans la base de donnee
 ////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

 	@PostMapping("/ville")
 	public String creer(@RequestBody Ville ville) throws JsonProcessingException {
 		Reponse<Ville> reponse;
 		System.out.println(ville);
 		try {

 			Ville d = villeMetier.creer(ville);
 			List<String> messages = new ArrayList<>();
 			messages.add(String.format("%s  à été créer avec succes", d.getId()));
 			reponse = new Reponse<Ville>(0, messages, d);

 		} catch (InvalideGstoreException e) {

 			reponse = new Reponse<Ville>(1, Static.getErreursForException(e), null);
 		}
 		return jsonMapper.writeValueAsString(reponse);
 	}
 	@PutMapping("/ville")
 	public String update(@RequestBody Ville  modif) throws JsonProcessingException {

 		Reponse<Ville> reponse = null;
 		Reponse<Ville> reponsePersModif = null;
 		// on recupere autre a modifier
 		System.out.println("modif recupere1:"+ modif);
 		reponsePersModif = getVilleById(modif.getId());
 		if (reponsePersModif.getBody() != null) {
 			try {
 				System.out.println("modif recupere2:"+ modif);
 				Ville ville = villeMetier.modifier(modif);
 				List<String> messages = new ArrayList<>();
 				messages.add(String.format("%s a modifier avec succes", ville.getId()));
 				reponse = new Reponse<Ville>(0, messages, ville);
 			} catch (InvalideGstoreException e) {

 				reponse = new Reponse<Ville>(1, Static.getErreursForException(e), null);
 			}

 		} else {
 			List<String> messages = new ArrayList<>();
 			messages.add(String.format("departement n'existe pas"));
 			reponse = new Reponse<Ville>(0, messages, null);
 		}

 		return jsonMapper.writeValueAsString(reponse);

 	}
 	// recherche les departements par id
 		@GetMapping("/ville/{id}")
 		public String getVById(@PathVariable("id") Long id) throws JsonProcessingException {

 			Reponse<Ville> reponse;

 			try {

 				Ville p = villeMetier.findById(id);
 				List<String> messages = new ArrayList<>();
 				messages.add(String.format(" ville recuperer"));
 				reponse = new Reponse<Ville>(0, messages, p);

 			} catch (Exception e) {

 				reponse = new Reponse<Ville>(1, Static.getErreursForException(e), null);
 			}
 			return jsonMapper.writeValueAsString(reponse);
 		}
 		// supprimer un departement
 				@DeleteMapping("/ville/{id}")
 				public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

 					Reponse<Boolean> reponse = null;

 					try {

 						reponse = new Reponse<Boolean>(0, null, villeMetier.supprimer(id));

 					} catch (RuntimeException e1) {
 						reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
 					}

 					return jsonMapper.writeValueAsString(reponse);
 				}

 	// get all departement
 		@GetMapping("/ville")
 		public String findAll() throws JsonProcessingException {
 			Reponse<List<Ville>> reponse;
 			try {
 				List<Ville> pers = villeMetier.findAll();
 				if (!pers.isEmpty()) {
 					reponse = new Reponse<List<Ville>>(0, null, pers);
 				} else {
 					List<String> messages = new ArrayList<>();
 					messages.add("Pas de departement enregistrés");
 					reponse = new Reponse<List<Ville>>(1, messages, new ArrayList<>());
 				}

 			} catch (Exception e) {
 				reponse = new Reponse<>(1, Static.getErreursForException(e), null);
 			}
 			return jsonMapper.writeValueAsString(reponse);

 		}
}
