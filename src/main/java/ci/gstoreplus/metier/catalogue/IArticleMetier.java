package ci.gstoreplus.metier.catalogue;

import java.util.List;

import ci.gstoreplus.entity.catalogue.Articles;
import ci.gstoreplus.metiers.Imetier;

public interface IArticleMetier extends Imetier<Articles, Long>{
	List<Articles> findArtclesByIdProduits(long id);
    List<Articles> findArtclesByMc(String nom);
    public List<Articles> recherchePrixMax(double prixmin, double prixmax);

}
