package ci.gstoreplus.controller.admin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.entity.shared.Personne;
import ci.gstoreplus.entity.shared.Role;
import ci.gstoreplus.entity.shared.RoleName;
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metier.admin.IRoleMetier;
import ci.gstoreplus.metier.admin.PersonneMetier;
import ci.gstoreplus.models.JwtAuthenticationResponse;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.security.admin.JwtTokenProvider;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PersonneMetier personneMetier;
	
	@Autowired
	IRoleMetier roleMetier;

	@Autowired
	JwtTokenProvider tokenProvider;
	@Autowired
	private ObjectMapper jsonMapper;

	@PostMapping("/signin")
	public String authenticateUser(@Valid @RequestBody Personne loginRequest) throws JsonProcessingException {
		Reponse<ResponseEntity<?>> reponse;
		Authentication authentication = authenticationManager.authenticate(

				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		reponse = new Reponse<ResponseEntity<?>>(0, null, ResponseEntity.ok(new JwtAuthenticationResponse(jwt)));
		return jsonMapper.writeValueAsString(reponse);

	}

	@PostMapping("/signup")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String creatUser(@RequestBody Personne signUpRequest) throws Exception {
		

		Reponse<Personne> reponse = null;
		Personne personne = null;
		try {

			Role userRole = roleMetier.findByName(RoleName.ROLE_ADMIN).get();
			signUpRequest.setRoles(Collections.singleton(userRole));
             personne = personneMetier.creer(signUpRequest);
			System.out.println("Voir le nom complet de la personne recuperée:" + personne.getNomComplet());
			

			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s  a été créé avec succès", personne.getId()));
			reponse = new Reponse<Personne>(0, messages, personne);

		} catch (InvalideGstoreException e) {
			reponse = new Reponse<Personne>(1, Static.getErreursForException(e), null);
		}
		return jsonMapper.writeValueAsString(reponse);
	}
	
}