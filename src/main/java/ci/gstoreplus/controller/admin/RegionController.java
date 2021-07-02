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

import ci.gstoreplus.entity.client.Region;
import ci.gstoreplus.entity.client.Ville;
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metier.admin.IRegionMetier;
import ci.gstoreplus.metier.admin.IVilleMetier;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RegionController {
	
    @Autowired
	private IRegionMetier regionMetier;
    @Autowired
	private ObjectMapper jsonMapper;
 // recuper Departement par identifiant
    
    
 	private Reponse<Region> getRegionById(Long id) {
 		Region region = null;

 		try {
 			region = regionMetier.findById(id);
 			if (region == null) {
 				List<String> messages = new ArrayList<>();
 				messages.add(String.format("region n'existe pas", id));
 				new Reponse<Region>(2, messages, null);

 			}
 		} catch (RuntimeException e) {
 			new Reponse<Region>(1, Static.getErreursForException(e), null);
 		}

 		return new Reponse<Region>(0, null, region);
 	}

 //////////////////////////////////////////////////////////////////////////////////////////////
 ////////////////// enregistrer un departement  dans la base de donnee
 ////////////////////////////////////////////////////////////////////////////////////////////// donnee////////////////////////////////

 	@PostMapping("/region")
 	public String creer(@RequestBody Region region) throws JsonProcessingException {
 		Reponse<Region> reponse;
 		System.out.println(region);
 		try {

 			Region d = regionMetier.creer(region);
 			List<String> messages = new ArrayList<>();
 			messages.add(String.format("%s  à été créer avec succes", d.getId()));
 			reponse = new Reponse<Region>(0, messages, d);

 		} catch (InvalideGstoreException e) {

 			reponse = new Reponse<Region>(1, Static.getErreursForException(e), null);
 		}
 		return jsonMapper.writeValueAsString(reponse);
 	}
 	@PutMapping("/region")
 	public String update(@RequestBody Region  modif) throws JsonProcessingException {

 		Reponse<Region> reponse = null;
 		Reponse<Region> reponsePersModif = null;
 		// on recupere autre a modifier
 		System.out.println("modif recupere1:"+ modif);
 		reponsePersModif = getRegionById(modif.getId());
 		if (reponsePersModif.getBody() != null) {
 			try {
 				System.out.println("modif recupere2:"+ modif);
 				Region region = regionMetier.modifier(modif);
 				List<String> messages = new ArrayList<>();
 				messages.add(String.format("%s a modifier avec succes", region.getId()));
 				reponse = new Reponse<Region>(0, messages, region);
 			} catch (InvalideGstoreException e) {

 				reponse = new Reponse<Region>(1, Static.getErreursForException(e), null);
 			}

 		} else {
 			List<String> messages = new ArrayList<>();
 			messages.add(String.format("Region n'existe pas"));
 			reponse = new Reponse<Region>(0, messages, null);
 		}

 		return jsonMapper.writeValueAsString(reponse);

 	}
 	// recherche les departements par id
 		@GetMapping("/region/{id}")
 		public String getRegionId(@PathVariable("id") Long id) throws JsonProcessingException {

 			Reponse<Region> reponse;

 			try {

 				Region p = regionMetier.findById(id);
 				List<String> messages = new ArrayList<>();
 				messages.add(String.format(" à été créer avec succes"));
 				reponse = new Reponse<Region>(0, messages, p);

 			} catch (Exception e) {

 				reponse = new Reponse<Region>(1, Static.getErreursForException(e), null);
 			}
 			return jsonMapper.writeValueAsString(reponse);
 		}
 		// supprimer un departement
 				@DeleteMapping("/region/{id}")
 				public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

 					Reponse<Boolean> reponse = null;

 					try {

 						reponse = new Reponse<Boolean>(0, null, regionMetier.supprimer(id));

 					} catch (RuntimeException e1) {
 						reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
 					}

 					return jsonMapper.writeValueAsString(reponse);
 				}

 	// get all departement
 		@GetMapping("/region")
 		public String findAll() throws JsonProcessingException {
 			Reponse<List<Region>> reponse;
 			try {
 				List<Region> pers = regionMetier.findAll();
 				if (!pers.isEmpty()) {
 					reponse = new Reponse<List<Region>>(0, null, pers);
 				} else {
 					List<String> messages = new ArrayList<>();
 					messages.add("Pas de departement enregistrés");
 					reponse = new Reponse<List<Region>>(1, messages, new ArrayList<>());
 				}

 			} catch (Exception e) {
 				reponse = new Reponse<>(1, Static.getErreursForException(e), null);
 			}
 			return jsonMapper.writeValueAsString(reponse);

 		}
}
