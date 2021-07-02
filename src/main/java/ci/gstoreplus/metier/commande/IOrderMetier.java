package ci.gstoreplus.metier.commande;

import java.util.List;

import ci.gstoreplus.entity.commande.Commande;
import ci.gstoreplus.entity.shared.Personne;
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metiers.Imetier;

public interface IOrderMetier extends Imetier<Commande, Long>{
	Commande creer(OrderForm orderForm) throws InvalideGstoreException;
	Commande findCommandeByIdPersonne(long id);

}
