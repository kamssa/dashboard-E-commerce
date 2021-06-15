package ci.gstoreplus.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.catalogue.ImageDetailRepository;
import ci.gstoreplus.entity.catalogue.ImageDetail;
import ci.gstoreplus.exception.InvalideGstoreException;

@Service
public class ImageDetailArticleMetierImpl implements ImageDetailArticleMetier{
@Autowired
private ImageDetailRepository imageDetailRepository;
	@Override
	public ImageDetail creer(ImageDetail entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return imageDetailRepository.save(entity);
	}

	@Override
	public ImageDetail modifier(ImageDetail entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return imageDetailRepository.save(entity);
	}

	@Override
	public List<ImageDetail> findAll() {
		// TODO Auto-generated method stub
		return imageDetailRepository.findAll();
	}

	@Override
	public ImageDetail findById(Long id) {
		// TODO Auto-generated method stub
		return imageDetailRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(List<ImageDetail> entites) {
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
	public List<ImageDetail> findImageByIdDetailArticles(Long id) {
		// TODO Auto-generated method stub
		return imageDetailRepository.findImageByIdDetailArticles(id);
	}

}
