package ci.gstoreplus.dao.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import ci.gstoreplus.entity.catalogue.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
	List<Image> findByIdArticles(long idArticles);
}
