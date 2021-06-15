package ci.gstoreplus.metier.catalogue;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ci.gstoreplus.dao.catalogue.ImageRepository;
import ci.gstoreplus.entity.catalogue.Image;

@Service
@Transactional
public class ImageService {
	@Autowired
	private ImageRepository imageRepository;

	public List<Image> list() {
		return imageRepository.findByOrderById();
	}

	public void save(Image image) {
		imageRepository.save(image);
	}
    public Optional<Image> findById(Long id){
	 return imageRepository.findById(id);
 }
	public void deleteById(Long id) {
		imageRepository.deleteById(id);
	}
	public boolean exists(Long id) {
		return imageRepository.existsById(id);
	}
	public Image findImageByIdTerrain(Long id) {
		return imageRepository.findImageByIdArticles(id);
	}

}
