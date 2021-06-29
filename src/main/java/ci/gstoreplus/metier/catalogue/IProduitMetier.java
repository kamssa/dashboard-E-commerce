package ci.gstoreplus.metier.catalogue;

import java.util.List;
import java.util.Map;

import ci.gstoreplus.entity.catalogue.Produits;
import ci.gstoreplus.entity.catalogue.SousCategories;
import ci.gstoreplus.metiers.Imetier;

public interface IProduitMetier extends Imetier<Produits, Long>{
	List<Produits> findProduitsByIdSousCategorie(long id);
	public Map<String, List<Produits>> uneOcurrenceAbonne();

}
