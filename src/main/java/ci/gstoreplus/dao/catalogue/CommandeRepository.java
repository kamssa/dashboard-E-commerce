package ci.gstoreplus.dao.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ci.gstoreplus.entity.commande.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long>{

}
