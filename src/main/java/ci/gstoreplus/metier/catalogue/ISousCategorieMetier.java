package ci.gstoreplus.metier.catalogue;

import java.util.List;
import java.util.Map;

import ci.gstoreplus.entity.catalogue.Categories;
import ci.gstoreplus.entity.catalogue.SousCategories;
import ci.gstoreplus.metiers.Imetier;

public interface ISousCategorieMetier extends Imetier<SousCategories, Long>{
	List<SousCategories> findSousCategoriesByIdCategorie(long id);
	public Map<Categories, List<SousCategories>> uneOcurrenceSc() ;
	  SousCategories findByNom(String nom);

}
