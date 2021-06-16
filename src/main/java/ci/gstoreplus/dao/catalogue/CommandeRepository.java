package ci.gstoreplus.dao.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.commande.Commande;

public interface CommandeRepository extends JpaRepository<Commande, Long>{

}
