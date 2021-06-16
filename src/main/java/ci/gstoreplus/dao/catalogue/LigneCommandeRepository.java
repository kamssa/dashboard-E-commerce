package ci.gstoreplus.dao.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.commande.LigneDeCommande;

public interface LigneCommandeRepository extends JpaRepository<LigneDeCommande, Long>{

}
