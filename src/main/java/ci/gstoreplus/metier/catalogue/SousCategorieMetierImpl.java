package ci.gstoreplus.metier.catalogue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import ci.gstoreplus.dao.catalogue.CatalogueRepository;
import ci.gstoreplus.dao.catalogue.ProduitRepository;
import ci.gstoreplus.dao.catalogue.SousCategorieRepository;
import ci.gstoreplus.entity.catalogue.Categories;
import ci.gstoreplus.entity.catalogue.Produits;
import ci.gstoreplus.entity.catalogue.SousCategories;
import ci.gstoreplus.exception.InvalideGstoreException;

@Service
public class SousCategorieMetierImpl  implements ISousCategorieMetier{
@Autowired
private SousCategorieRepository sousCategorieRepository;
@Autowired
private ProduitRepository produitRepository;
@Autowired
private CatalogueRepository catalogueRepository;

	@Override
	public SousCategories creer(SousCategories entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return sousCategorieRepository.save(entity);
	}
    @Transactional
	@Override
	public SousCategories modifier(SousCategories entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return sousCategorieRepository.save(entity);
	}

	@Override
	public List<SousCategories> findAll() {
		// TODO Auto-generated method stub
		return sousCategorieRepository.findAll();
	}

	@Override
	public SousCategories findById(Long id) {
		// TODO Auto-generated method stub
		return sousCategorieRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(List<SousCategories> entites) {
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
	public Map<Categories, List<SousCategories>> uneOcurrenceSc() {
		//List<SousCategories> sousCategories = sousCategorieRepository.findSousCategoriesByIdCategorie(id);
        List<Categories> categories = catalogueRepository.findAll();
		Categories c = null;
		SousCategories sc = null;
		Map<Categories, List<SousCategories>> map = new HashMap<>();
		List<SousCategories> scs = Lists.newArrayList();
		for (Categories cat : categories) {
          map.put(cat,  sousCategorieRepository.findSousCategoriesByIdCategorie(cat.getId()));
	     }
		 return map;
	}
	@Override
	public List<SousCategories> findSousCategoriesByIdCategorie(long id) {
		// TODO Auto-generated method stub
		return sousCategorieRepository.findSousCategoriesByIdCategorie(id);
	}
	@Override
	public SousCategories findByNom(String nom) {
		// TODO Auto-generated method stub
		return sousCategorieRepository.findByNom(nom).get();
	}
	

}
