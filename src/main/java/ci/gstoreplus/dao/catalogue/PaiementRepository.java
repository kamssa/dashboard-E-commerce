package ci.gstoreplus.dao.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ci.gstoreplus.entity.commande.Paiement;



@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
	
		//obtenir la commade d'un paiement
		@Query("select p from Paiement p where p.commande.id=?1")
		Paiement getPaiementDeCommande(Long id);
}
