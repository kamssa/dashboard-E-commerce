package ci.gstoreplus.dao.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ci.gstoreplus.entity.shared.VerificationToken;





@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
	@Query("select v from VerificationToken v where v.id=?1")
	VerificationToken findByTokenById(Long id);
	@Query("select v from VerificationToken v where v.token=?1")
	VerificationToken findByToken(String token);
	@Query("select v from VerificationToken v where v.personne.id=?1")
    VerificationToken rechercherByPersonne(Long id);
}
