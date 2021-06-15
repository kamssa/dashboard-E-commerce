package ci.gstoreplus.metier.catalogue;

import ci.gstoreplus.entity.catalogue.DetailArticles;
import ci.gstoreplus.metiers.Imetier;

public interface IDetailArticlesMetier extends Imetier<DetailArticles, Long>{
	DetailArticles findDetailArticleByIdArticle(long id);
 
}
