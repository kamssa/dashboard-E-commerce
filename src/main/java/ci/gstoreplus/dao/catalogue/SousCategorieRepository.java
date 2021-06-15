package ci.gstoreplus.dao.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.catalogue.SousCategories;

public interface SousCategorieRepository  extends JpaRepository<SousCategories, Long>{

}
