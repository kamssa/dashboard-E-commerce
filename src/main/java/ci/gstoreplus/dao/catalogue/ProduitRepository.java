package ci.gstoreplus.dao.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.catalogue.Produits;

public interface ProduitRepository extends JpaRepository<Produits, Long> {

}
