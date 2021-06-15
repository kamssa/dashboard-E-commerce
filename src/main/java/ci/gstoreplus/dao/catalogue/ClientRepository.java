package ci.gstoreplus.dao.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ci.gstoreplus.entity.shared.Personne;


@Repository
public interface ClientRepository extends JpaRepository<Personne, Long> {

}
