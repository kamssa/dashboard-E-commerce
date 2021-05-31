package ci.gstoreplus.dao.admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.shared.Role;
import ci.gstoreplus.entity.shared.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(RoleName roleName);
}
