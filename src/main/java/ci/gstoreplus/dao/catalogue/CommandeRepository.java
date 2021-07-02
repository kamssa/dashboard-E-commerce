package ci.gstoreplus.dao.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ci.gstoreplus.entity.commande.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long>{
	@Query("select c from Commande c  where c.personne.id=?1")
	Commande findCommandeByIdPersonne(long id);

}
