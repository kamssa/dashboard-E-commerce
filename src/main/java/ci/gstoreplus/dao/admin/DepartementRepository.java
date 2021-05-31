package ci.gstoreplus.dao.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.admin.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Long>{

}
