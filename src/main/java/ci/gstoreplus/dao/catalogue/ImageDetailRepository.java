package ci.gstoreplus.dao.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import ci.gstoreplus.entity.catalogue.ImageDetail;

public interface ImageDetailRepository extends JpaRepository<ImageDetail, Long>{
	List<ImageDetail> findByOrderById();
	//recupere l'image par id de article
		@Query("select image from ImageDetail image  where image.idDetailArticle=?1")
		List<ImageDetail> findImageByIdDetailArticles(Long id);
}
