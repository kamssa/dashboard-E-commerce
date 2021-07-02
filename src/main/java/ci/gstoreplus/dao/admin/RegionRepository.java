package ci.gstoreplus.dao.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.client.Region;

public interface RegionRepository extends JpaRepository<Region, Long>{

}
