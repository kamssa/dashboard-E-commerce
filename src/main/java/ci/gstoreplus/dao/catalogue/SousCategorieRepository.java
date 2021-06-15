package ci.gstoreplus.dao.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.catalogue.SousCategories;

public interface SousCategorieRepository  extends JpaRepository<SousCategories, Long>{
	@Query("select sc from SousCategories sc  where sc.categories.id=?1")
	List<SousCategories> findSousCategoriesByIdCategorie(long id);
}
