package ci.gstoreplus.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.catalogue.ImageDetail;
import ci.gstoreplus.metiers.Imetier;

public interface ImageDetailArticleMetier extends Imetier<ImageDetail, Long>{
	List<ImageDetail> findImageByIdDetailArticles(Long id);

}
