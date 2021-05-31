package ci.gstoreplus.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.catalogue.Image;
import ci.gstoreplus.metiers.Imetier;

public interface ImageMetier extends Imetier<Image, Long>{
	List<Image> findByIdArticles(long idArticles);

}
