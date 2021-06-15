package ci.gstoreplus.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.catalogue.SousCategories;
import ci.gstoreplus.metiers.Imetier;

public interface ISousCategorieMetier extends Imetier<SousCategories, Long>{
	List<SousCategories> findSousCategoriesByIdCategorie(long id);

}
