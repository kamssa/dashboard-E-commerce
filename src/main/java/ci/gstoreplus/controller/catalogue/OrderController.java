package ci.gstoreplus.controller.catalogue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ci.gstoreplus.entity.catalogue.Articles;
import ci.gstoreplus.entity.client.Client;
import ci.gstoreplus.entity.commande.Commande;
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metier.commande.IOrderMetier;
import ci.gstoreplus.metier.commande.OrderForm;
import ci.gstoreplus.models.Reponse;
import ci.gstoreplus.utilitaire.Static;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {
	@Autowired
    private  IOrderMetier orderMetier;
	@Autowired
	private ObjectMapper jsonMapper;
	
	 @PostMapping("/orders")
	    public String saveOrder(@RequestBody OrderForm orderForm) throws JsonProcessingException{
	        System.out.println("Voir la commande:" + orderForm);

	    	Reponse<Commande> reponse;
			System.out.println(orderForm);
			try {

				Commande cat = orderMetier.creer(orderForm);
				System.out.println("order enregistrer:" +cat);
				List<String> messages = new ArrayList<>();
				messages.add(String.format("%s  à été créer avec succes", cat.getId()));
				reponse = new Reponse<Commande>(0, messages, cat);

			} catch (InvalideGstoreException e) {

				reponse = new Reponse<Commande>(1, Static.getErreursForException(e), null);
			}
			return jsonMapper.writeValueAsString(reponse);
	    }
	    @DeleteMapping("/order/{id}")
		public String supprimer(@PathVariable("id") Long id) throws JsonProcessingException {

			Reponse<Boolean> reponse = null;

			try {

				reponse = new Reponse<Boolean>(0, null, orderMetier.supprimer(id));

			} catch (RuntimeException e1) {
				reponse = new Reponse<>(3, Static.getErreursForException(e1), null);
			}

			return jsonMapper.writeValueAsString(reponse);
		}
}
