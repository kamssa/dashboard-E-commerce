package ci.gstoreplus.metier.catalogue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.catalogue.ProduitRepository;
import ci.gstoreplus.entity.catalogue.Produits;
import ci.gstoreplus.exception.InvalideGstoreException;

@Service
public class ProduitMetierImpl implements IProduitMetier{
@Autowired
private ProduitRepository produitRepository;
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

}
