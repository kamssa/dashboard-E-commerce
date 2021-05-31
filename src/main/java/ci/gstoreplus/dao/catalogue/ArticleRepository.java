package ci.gstoreplus.dao.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.gstoreplus.entity.catalogue.Articles;

public interface ArticleRepository extends JpaRepository<Articles, Long>{

}
