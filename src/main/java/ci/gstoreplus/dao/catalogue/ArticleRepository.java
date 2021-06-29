package ci.gstoreplus.dao.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.catalogue.Articles;


public interface ArticleRepository extends JpaRepository<Articles, Long>{
	@Query("select a from Articles a  where a.produits.id=?1")
	List<Articles> findArtclesByIdProduits(long id);
	 @Query("select a from Articles a where a.nom LIKE %?1%")
     List<Articles> findArtclesByMc(String nom);

}
