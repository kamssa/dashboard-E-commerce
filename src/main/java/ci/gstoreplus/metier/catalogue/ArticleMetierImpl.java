package ci.gstoreplus.metier.catalogue;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.catalogue.ArticleRepository;
import ci.gstoreplus.entity.catalogue.Articles;
import ci.gstoreplus.exception.InvalideGstoreException;

@Service
public class ArticleMetierImpl implements IArticleMetier{
@Autowired
private ArticleRepository articleRepository;
	@Override
	public Articles creer(Articles entity) throws InvalideGstoreException {
		
		return articleRepository.save(entity);
	}

	@Override
	public Articles modifier(Articles entity) throws InvalideGstoreException {
		return articleRepository.save(entity);
	}

	@Override
	public List<Articles> findAll() {
		return articleRepository.findAll();
	}

	@Override
	public Articles findById(Long id) {
		return articleRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		articleRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Articles> entites) {
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
		return null;
	}

	@Override
	public List<Articles> findArtclesByIdProduits(long id) {
		return articleRepository.findArtclesByIdProduits(id);
	}

	@Override
	public List<Articles> findArtclesByMc(String nom) {
		// TODO Auto-generated method stub
		return articleRepository.findArtclesByMc(nom);
	}
	@Override
	public List<Articles> recherchePrixMax(double prixmin, double prixmax) {
		List<Articles> arts = null;
		List<Articles> articles = articleRepository.findAll();
		arts = articles.stream().filter(t -> t.getPrixUnitaire() > prixmin)
	    		.filter(t -> t.getPrixUnitaire()< prixmax)
	    		.collect(Collectors.toList());
	    return arts;			
	}

}
