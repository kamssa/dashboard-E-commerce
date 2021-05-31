package ci.gstoreplus.metier.admin;

import java.util.Optional;

import ci.gstoreplus.entity.shared.Role;
import ci.gstoreplus.entity.shared.RoleName;
import ci.gstoreplus.metiers.Imetier;



public interface IRoleMetier  extends Imetier<Role, Long>{
	Optional<Role> findByName(RoleName roleName);

}
