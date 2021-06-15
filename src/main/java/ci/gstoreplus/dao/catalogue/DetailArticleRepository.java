package ci.gstoreplus.dao.catalogue;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ci.gstoreplus.entity.catalogue.DetailArticles;

public interface DetailArticleRepository extends JpaRepository<DetailArticles, Long>{
	@Query("select d from DetailArticles d  where d.articles.id=?1")
	DetailArticles findDetailArticleByIdArticle(long id);
}
