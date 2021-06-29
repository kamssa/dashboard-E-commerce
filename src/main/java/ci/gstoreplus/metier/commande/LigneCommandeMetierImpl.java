package ci.gstoreplus.metier.commande;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.entity.commande.LigneDeCommande;
import ci.gstoreplus.exception.InvalideGstoreException;


@Service
public class LigneCommandeMetierImpl implements ILigneCommandeMetier {
@Autowired
ci.gstoreplus.dao.catalogue.LigneCommandeRepository ligneCommandeRepository;
	@Override
	public LigneDeCommande creer(LigneDeCommande entity) throws InvalideGstoreException {
		
		return ligneCommandeRepository.save(entity);
	}
	@Override
	public LigneDeCommande modifier(LigneDeCommande entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return ligneCommandeRepository.save(entity);
	}
	@Override
	public List<LigneDeCommande> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public LigneDeCommande findById(Long id) {
	return	ligneCommandeRepository.findById(id).get();
	}
	@Override
	public boolean supprimer(Long id) {
    ligneCommandeRepository.deleteById(id);
		return true;
	}
	@Override
	public boolean supprimer(List<LigneDeCommande> entites) {
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
