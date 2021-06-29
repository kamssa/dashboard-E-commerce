package ci.gstoreplus.metier.catalogue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.catalogue.ProduitRepository;
import ci.gstoreplus.dao.catalogue.SousCategorieRepository;
import ci.gstoreplus.entity.catalogue.Categories;
import ci.gstoreplus.entity.catalogue.Produits;
import ci.gstoreplus.entity.catalogue.SousCategories;
import ci.gstoreplus.exception.InvalideGstoreException;
import com.google.common.collect.Lists;
@Service
public class ProduitMetierImpl implements IProduitMetier{
@Autowired
private ProduitRepository produitRepository;
@Autowired
private SousCategorieRepository sousCategorieRepository;
	@Override
	public Produits creer(Produits entity) throws InvalideGstoreException {
		return produitRepository.save(entity);
	}

	@Override
	public Produits modifier(Produits entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return produitRepository.save(entity);
	}

	@Override
	public List<Produits> findAll() {
		// TODO Auto-generated method stub
		return produitRepository.findAll();
	}

	@Override
	public Produits findById(Long id) {
		// TODO Auto-generated method stub
		return produitRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		produitRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Produits> entites) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existe(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean existsByPseudo(String pseudo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produits> findProduitsByIdSousCategorie(long id) {
		// TODO Auto-generated method stub
		return produitRepository.findProduitsByIdSousCategorie(id);
	}
	@Override
	public Map<String, List<Produits>> uneOcurrenceAbonne() {
		List<SousCategories> sousCategories = sousCategorieRepository.findAll();
         SousCategories sc = null;
         Categories c = null;
		Produits p = null;
		Map<String, List<Produits>> map = new HashMap<>();
		for (SousCategories scat : sousCategories) {
          List<Produits> produitR= produitRepository.findProduitsByIdSousCategorie(scat.getId());
          map.put(scat.getNom(),  produitR);
	     }
		 return map;
	}
	

}
