package ci.gstoreplus.metier.catalogue;


import ci.gstoreplus.entity.catalogue.Categories;
import ci.gstoreplus.metiers.Imetier;

public interface ICategorieMetier extends Imetier<Categories, Long>{
	  Categories findByNom(String nom);

}
