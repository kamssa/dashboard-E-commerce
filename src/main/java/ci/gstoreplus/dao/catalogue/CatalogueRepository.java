package ci.gstoreplus.dao.catalogue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.catalogue.Categories;

public interface CatalogueRepository extends JpaRepository<Categories, Long>{
  Optional<Categories> findByNom(String nom);
}
