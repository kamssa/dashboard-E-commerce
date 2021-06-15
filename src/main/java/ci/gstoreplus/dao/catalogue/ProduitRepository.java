package ci.gstoreplus.dao.catalogue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ci.gstoreplus.entity.catalogue.Produits;

public interface ProduitRepository extends JpaRepository<Produits, Long> {
	@Query("select p from Produits p  where p.sousCategories.id=?1")
	List<Produits> findProduitsByIdSousCategorie(long id);
}
