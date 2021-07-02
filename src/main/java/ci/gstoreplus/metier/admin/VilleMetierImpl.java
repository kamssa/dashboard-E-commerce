package ci.gstoreplus.metier.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.admin.VilleRepository;
import ci.gstoreplus.entity.client.Ville;
import ci.gstoreplus.exception.InvalideGstoreException;

@Service
public class VilleMetierImpl implements IVilleMetier{
    @Autowired  
	private VilleRepository villeRepository;
	@Override
	public Ville creer(Ville entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return villeRepository.save(entity);
	}

	@Override
	public Ville modifier(Ville entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return villeRepository.save(entity);
	}

	@Override
	public List<Ville> findAll() {
		// TODO Auto-generated method stub
		return villeRepository.findAll();
	}

	@Override
	public Ville findById(Long id) {
		// TODO Auto-generated method stub
		return villeRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(List<Ville> entites) {
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
