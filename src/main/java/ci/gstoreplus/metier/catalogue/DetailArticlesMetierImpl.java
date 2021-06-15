package ci.gstoreplus.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.catalogue.DetailArticleRepository;
import ci.gstoreplus.entity.catalogue.DetailArticles;
import ci.gstoreplus.exception.InvalideGstoreException;

@Service
public class DetailArticlesMetierImpl implements IDetailArticlesMetier{
@Autowired
private DetailArticleRepository detailArticleRepository;
	@Override
	public DetailArticles creer(DetailArticles entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return detailArticleRepository.save(entity);
	}

	@Override
	public DetailArticles modifier(DetailArticles entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return detailArticleRepository.save(entity);
	}

	@Override
	public List<DetailArticles> findAll() {
		// TODO Auto-generated method stub
		return detailArticleRepository.findAll();
	}

	@Override
	public DetailArticles findById(Long id) {
		// TODO Auto-generated method stub
		return detailArticleRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
    detailArticleRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<DetailArticles> entites) {
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
	public DetailArticles findDetailArticleByIdArticle(long id) {
		// TODO Auto-generated method stub
		return detailArticleRepository.findDetailArticleByIdArticle(id);
	}

}
