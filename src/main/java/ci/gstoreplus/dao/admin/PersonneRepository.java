package ci.gstoreplus.dao.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.shared.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long>{
	// recupere une personne par son nom
	Optional<Personne> findByNom(String nom);

	// recupere une personne par son type et son nomComplet
	@Query("select p from Personne p where p.type = ?1 AND p.nomComplet like %?2%")
	List<Personne> findAllPersonnesParMc(String type, String mc);

	// liste des personne par leur nom complet
	List<Personne> findByNomCompletContainingIgnoreCase(String nomcomplet);

	// liste des personne de la base a partir de id
	List<Personne> findByIdIn(List<Long> userIds);

	Optional<Personne> findByEmail(String email);
	

	// liste des personne de la base a partir de login et telephone
	Optional<Personne> findByEmailOrTelephone(String email, String telephone);

	// verifier si une personne existe a partir de son login
	Boolean existsByEmail(String email);

	}
