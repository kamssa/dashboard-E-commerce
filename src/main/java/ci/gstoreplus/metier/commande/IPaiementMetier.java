package ci.gstoreplus.metier.commande;

import ci.gstoreplus.entity.commande.Paiement;
import ci.gstoreplus.metiers.Imetier;

public interface IPaiementMetier extends Imetier<Paiement, Long> {
Paiement getPaiementDeCommande(Long id);
}
