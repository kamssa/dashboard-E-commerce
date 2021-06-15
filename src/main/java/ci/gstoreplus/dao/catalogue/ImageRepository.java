package ci.gstoreplus.dao.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.catalogue.Image;

public interface ImageRepository extends JpaRepository<Image, Long>{
	List<Image> findByIdArticles(long idArticles);
	List<Image> findByOrderById();
	//recupere l'image par id de article
		@Query("select image from Image image  where image.idArticles=?1")
		Image findImageByIdArticles(Long id);
}
