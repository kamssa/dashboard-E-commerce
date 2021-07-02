package ci.gstoreplus.controller.catalogue;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.entity.catalogue.Articles;
import ci.gstoreplus.entity.commande.Commande;
import ci.gstoreplus.entity.commande.Paiement;
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metier.commande.IOrderMetier;
import ci.gstoreplus.metier.commande.IPaiementMetier;
import ci.gstoreplus.metier.commande.OrderForm;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.models.ReponsePaiement;
import ci.gstoreplus.utilitaire.Static;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class PaiementController {
	@Autowired
	IPaiementMetier paiementMetier;
	@Autowired
	IOrderMetier commandeMetier;
	@Autowired
	private ObjectMapper jsonMapper;

	private Reponse<Paiement> getPaiementById(Long id) {
		Paiement paiement = null;
		try {
			paiement = paiementMetier.findById(id);
		} catch (RuntimeException e) {
			new Reponse<>(1, Static.getErreursForException(e), null);
		}
		if (paiement == null) {
			List<String> messages = new ArrayList<>();
			messages.add(String.format("le paiement n'existe pas", id));
			new Reponse<>(2, messages, null);

		}
		return new Reponse<Paiement>(0, null, paiement);
	}

	@PostMapping("/paiement")
	public String creer(@RequestBody OrderForm orderForm , @RequestParam Long id)
			throws InvalideGstoreException, IOException {
		System.out.println("commande"+ orderForm);
		Reponse<ResponseEntity<String>> reponse = null;
		Reponse<Paiement> reponsePaie = null;
		ReponsePaiement<Paiement, String> reponsePaiement = null;
		Reponse<String> reponseSignature = null;
		Reponse<List<String>> listString;
		Commande commande = commandeMetier.findById(id);
		System.out.println("Voir la commande"+ commande);
		Paiement paye = null;
        Paiement p= new Paiement();
        System.out.println("Voir le paiement créer"+ p);
        double montantCommande = commande.getTotalAmount();
		int montant = (int) montantCommande;
		 String trans_id = UUID.randomUUID().toString();
		p.setCpm_amount(montant);
		p.setCommande(commande);
		p.setCpm_trans_id(trans_id);
		

		try {
			String url = "https://api.cinetpay.com/v1/?method=getSignatureByPost";
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<Object, Object> map = new LinkedMultiValueMap<Object, Object>();
			map.add("apikey", p.getApikey());
			map.add("cpm_amount", Integer.toString(p.getCpm_amount()));
			map.add("cpm_currency", p.getCpm_currency());
			map.add("cpm_custom", p.getCpm_custom());
			map.add("cpm_designation", p.getCpm_designation());
			map.add("cpm_language", p.getCpm_language());
			map.add("cpm_page_action", p.getCpm_page_action());
			map.add("cpm_payment_config", p.getCpm_payment_config());
			map.add("cpm_site_id", Integer.toString(p.getCpm_site_id()));
			map.add("cpm_trans_date", p.getCpm_trans_date());
			map.add("cpm_trans_id",p.getCpm_trans_id());
			map.add("cpm_version", p.getCpm_version());
			//.add("notify_url", paiement.getNotify_url());

			HttpEntity<MultiValueMap<Object, Object>> request = new HttpEntity<MultiValueMap<Object, Object>>(map,
					headers);
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
			List<String> messages = new ArrayList<>();
			messages.add(String.format("%s %s %s", p.getCpm_trans_date(),p.getCpm_trans_id(),p.getCpm_amount()));
			String signature = response.getBody();

			reponseSignature = new Reponse<String>(0, messages, signature);
			reponsePaie = new Reponse<Paiement>(0, messages, p);
			p.getCommande().setPaye(false);
		   //Commande com=	paye.getCommande();
			p.setSignature(signature);
		    
            Paiement paie= paiementMetier.creer(p);
    		System.out.println("Voir la signature"+ paie.getSignature());
            paie.setCpm_amount(paie.getCpm_amount());
            paie.setCpm_site_id(paie.getCpm_site_id());
            paie.setCpm_trans_id(paie.getCpm_trans_id());
            paie.setCpm_trans_date(paie.getCpm_trans_date());
          
            
		    reponsePaie = new Reponse<Paiement>(0, messages, paie);
			reponse = new Reponse<ResponseEntity<String>>(0,null, response);
			reponsePaiement = new ReponsePaiement<Paiement, String>(0,null, paie, signature);
			
            
		} catch (Exception e) {
			reponse = new Reponse<ResponseEntity<String>>(1, Static.getErreursForException(e), null);
		}
        
		return jsonMapper.writeValueAsString(reponsePaiement);
		
	}
	
	@PostMapping("/reponseCinetPay")
	public String creerReponse(@RequestBody Paiement paiement)
			throws InvalideGstoreException, IOException {
		
		Reponse<Paiement> reponse = null;
		

		try {
			
			reponse = new Reponse<Paiement>(0,null,null);
			//creeAbonne.creerUnAbonne(commande.getPersonne());
		} catch (Exception e) {
			reponse = new Reponse<Paiement>(1, Static.getErreursForException(e), null);
		}
        
		return jsonMapper.writeValueAsString(reponse);
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////
	// modifier un paiement dans la base de donnee
	///////////////////////////////////////////////////////////////////////////////////////// ///////////////////////////////////////////

	@PostMapping("/payer")
		public String modfier(@RequestBody Paiement paiement)
				throws InvalideGstoreException, IOException {
			Reponse<ResponseEntity<String>> reponse = null;
			Reponse<Paiement> reponsePaie = null;
			ReponsePaiement<Paiement, String> reponsePaiement = null;
			Reponse<String> reponseSignature = null;
			Reponse<List<String>> listString;
			Paiement p = paiementMetier.findById(paiement.getId());
			Paiement paye = null;
			//paiement.setCpm_amount(montant);
			//paiement.setCommande(commande);
			//paiement.setCpm_trans_id(commande.getNumero());
			

			try {
				String url = "https://secure.cinetpay.com";
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				MultiValueMap<Object, Object> map = new LinkedMultiValueMap<Object, Object>();
				map.add("apikey", p.getApikey());
				map.add("cpm_amount", Integer.toString(p.getCpm_amount()));
				map.add("cpm_currency", p.getCpm_currency());
				map.add("cpm_custom", p.getCpm_custom());
				map.add("cpm_designation", p.getCpm_designation());
				map.add("cpm_language", p.getCpm_language());
				map.add("cpm_page_action", p.getCpm_page_action());
				map.add("cpm_payment_config", p.getCpm_payment_config());
				map.add("cpm_site_id", Integer.toString(p.getCpm_site_id()));
				map.add("cpm_trans_date", p.getCpm_trans_date());
				map.add("cpm_trans_id",p.getCpm_trans_id());
				map.add("cpm_version", p.getCpm_version());
				map.add("signature", p.getSignature());
				//.add("notify_url", paiement.getNotify_url());

				HttpEntity<MultiValueMap<Object, Object>> request = new HttpEntity<MultiValueMap<Object, Object>>(map,
						headers);
				RestTemplate restTemplate = new RestTemplate();
				ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
				
				//Paiement retour= new Paiement();
				//paye.setCpm_amount(paiement.getCpm_amount());
				//paye.setCpm_trans_date(paiement.getCpm_trans_date());
				//paye.setCpm_trans_id(paiement.getCpm_trans_id());
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s %s %s", p.getCpm_trans_date(),p.getCpm_trans_id(),p.getCpm_amount()));
				String signature = response.getBody();

				reponseSignature = new Reponse<String>(0, messages, signature);
				reponsePaie = new Reponse<Paiement>(0, messages, p);
				p.getCommande().setPaye(true);
			   //Commande com=	paye.getCommande();
				/*reponsePaiement = new ReponsePaiement<Paiement, String>(0,null, paiement, signature);
				paiement.setSignature(reponseSignature.getBody());
	            System.out.println("signature*****************"+reponsePaiement.getBody().getSignature());
			    */
	            Paiement paie= paiementMetier.modifier(p);
			    reponsePaie = new Reponse<Paiement>(0, messages, p);
				reponse = new Reponse<ResponseEntity<String>>(0,null, response);
				
				//creeAbonne.creerUnAbonne(commande.getPersonne());
			} catch (Exception e) {
				reponse = new Reponse<ResponseEntity<String>>(1, Static.getErreursForException(e), null);
			}
	        
			return jsonMapper.writeValueAsString(reponse);
			
		}
 ////////recuperer un paiement par son id
 @GetMapping("/paiement/{id}")
 public String getPaiById(@PathVariable Long id) throws JsonProcessingException {
	// Annotation @PathVariable permet de recuperer le paremettre dans URI
	Reponse<Paiement> reponse = null;

	reponse = getPaiementById(id);
	if (reponse.getBody() == null) {
		throw new RuntimeException("pas d'enregistrement pour ce paiement");
	}

	return jsonMapper.writeValueAsString(reponse);

}
	// supprimer une categorie
		@DeleteMapping("/paiement/{id}")
		public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Boolean> reponse = null;

			try {

				reponse = new Reponse<Boolean>(0, null, paiementMetier.supprimer(id));

			} catch (RuntimeException e1) {
				reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
			}

			return jsonMapper.writeValueAsString(reponse);
		}

}
