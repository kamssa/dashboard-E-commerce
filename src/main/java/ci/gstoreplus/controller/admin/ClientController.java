package ci.gstoreplus.controller.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
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

import ci.gstoreplus.entity.client.Client;
import ci.gstoreplus.entity.shared.Personne;
import ci.gstoreplus.entity.shared.Role;
import ci.gstoreplus.entity.shared.RoleName;
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metier.admin.IRoleMetier;
import ci.gstoreplus.metier.catalogue.ClientMetier;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.security.admin.JwtTokenProvider;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClientController {
	@Autowired
	AuthenticationManager authenticationManager;
    @Autowired
	private ClientMetier personneMetier;
    @Autowired
	IRoleMetier roleMetier;
    @Autowired
	JwtTokenProvider tokenProvider;
	@Autowired
	private ObjectMapper jsonMapper;
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	// recuper Departement par identifiant
		private Reponse<Personne> getClientById(Long id) {
			Personne personne = null;

			try {
				personne = personneMetier.findById(id);
				if (personne == null) {
					List<String> messages = new ArrayList<>();
					messages.add(String.format("Personne n'existe pas", id));
					new Reponse<Personne>(2, messages, null);

				}
			} catch (RuntimeException e) {
				new Reponse<Personne>(1, Static.getErreursForException(e), null);
			}

			return new Reponse<Personne>(0, null, personne);
		}

	
	@PostMapping("/client")
	public String creer(@RequestBody Personne client) throws JsonProcessingException {
		Reponse<Personne> reponse;
		System.out.println(client);
		try {

			Personne d = personneMetier.creer(client);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  à été créer avec succes", d.getId()));
			reponse = new Reponse<Personne>(0, messages, d);

		} catch (InvalideGstoreException e) {

			reponse = new Reponse<Personne>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}
	@PutMapping("/client")
	public String update(@RequestBody Personne  modif) throws JsonProcessingException {

		Reponse<Personne> reponse = null;
		Reponse<Personne> reponsePersModif = null;
		// on recupere autre a modifier
		System.out.println("modif recupere1: Voir type: "+ modif);
		reponsePersModif = getClientById(modif.getId());
		if (reponsePersModif.getBody() != null) {
			try {
				System.out.println("modif recupere2:"+ modif);
                Role userRole = roleMetier.findByName(RoleName.ROLE_CLIENT).get();
				modif.setRoles(Collections.singleton(userRole));
				Personne personne = personneMetier.modifier(modif);
				System.out.println("personne modifiée:"+ personne);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s a modifier avec succes", personne.getId()));
				reponse = new Reponse<Personne>(0, messages, personne);
			} catch (InvalideGstoreException e) {

				reponse = new Reponse<Personne>(1, Static.getErreursForException(e), null);
			}

		} else {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("client n'existe pas"));
			reponse = new Reponse<Personne>(0, messages, null);
		}

		return jsonMapper.writeValueAsString(reponse);

	}
	// get all demande
	@GetMapping("/client")
	public String findAll() throws JsonProcessingException {
		Reponse<List<Personne>> reponse;
		try {
			List<Personne> clients = personneMetier.findAll();
			if (!clients.isEmpty()) {
				reponse = new Reponse<List<Personne>>(0, null, clients);
			} else {
				List<String> messages = new ArrayList<>();
				messages.add("Pas de client enregistrés");
				reponse = new Reponse<List<Personne>>(1, messages, new ArrayList<>());
			}

		} catch (Exception e) {
			reponse = new Reponse<>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);

	}
	// recherche le membre par id
			@GetMapping("/getClient/{email}")
			public String getClientByEmail(@PathVariable("email") String email) throws JsonProcessingException {

				Reponse<Personne> reponse;

				try {

					Personne p = personneMetier.findByEmail(email);
					 System.out.println("getClientById:" +p);
					List<String> messages = new ArrayList<>();
					messages.add(String.format(" à été créer avec succes"));
					reponse = new Reponse<Personne>(0, messages, p);

				} catch (Exception e) {

					reponse = new Reponse<Personne>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);
			}
			// recherche les personne par id
			@GetMapping("/client/{id}")
			public String getPersonneById(@PathVariable("id") Long id) throws JsonProcessingException {
            
				Reponse<Personne> reponse;

				try {

					Personne p = personneMetier.findById(id);
					 System.out.println("getClientById:" +p);
					List<String> messages = new ArrayList<>();
					messages.add(String.format(" à été créer avec succes"));
					reponse = new Reponse<Personne>(0, messages, p);

				} catch (Exception e) {

					reponse = new Reponse<Personne>(1, Static.getErreursForException(e), null);
				}
				return jsonMapper.writeValueAsString(reponse);
			}
	// supprimer une demande
	@DeleteMapping("/client/{id}")
	public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

		Reponse<Boolean> reponse = null;

		try {

			reponse = new Reponse<Boolean>(0, null, personneMetier.supprimer(id));

		} catch (RuntimeException e1) {
			reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
		}

		return jsonMapper.writeValueAsString(reponse);
	}
}
