package ci.gstoreplus.metier.catalogue;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.catalogue.CatalogueRepository;
import ci.gstoreplus.entity.catalogue.Categories;
import ci.gstoreplus.exception.InvalideGstoreException;

@Service
public class CategorieMetierImpl implements ICategorieMetier {
	@Autowired
	private CatalogueRepository catalogueRepository;

	// enregistrer une catégorie dans la bese
	@Override
	public Categories creer(Categories cat) throws InvalideGstoreException {
		return catalogueRepository.save(cat);		
	}
	
	// faire la mise à jour d'une catégorie dans la base
    @Override
	public Categories modifier(Categories entity) throws InvalideGstoreException {
		return catalogueRepository.save(entity);
	}

	@Override
	public List<Categories> findAll() {
		return catalogueRepository.findAll();
	}

	@Override
	public Categories findById(Long id) {
		return catalogueRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		catalogueRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Categories> entites) {
		return true;
	}

	@Override
	public boolean existe(Long id) {
		catalogueRepository.existsById(id);
		return true;
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
	public Categories findByNom(String nom) {
		// TODO Auto-generated method stub
		return catalogueRepository.findByNom(nom).get();
	}

}
