package ci.gstoreplus.metier.commande;

import ci.gstoreplus.entity.commande.Commande;
import ci.gstoreplus.entity.shared.Personne;
import ci.gstoreplus.exception.InvalideGstoreException;
import ci.gstoreplus.metiers.Imetier;

public interface IOrderMetier extends Imetier<Commande, Long>{
	Commande creer(Personne client, OrderForm orderForm) throws InvalideGstoreException;

}
