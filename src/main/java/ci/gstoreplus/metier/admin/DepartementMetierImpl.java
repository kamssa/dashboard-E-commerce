package ci.gstoreplus.metier.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.admin.DepartementRepository;
import ci.gstoreplus.entity.admin.Departement;
import ci.gstoreplus.exception.InvalideGstoreException;

@Service
public class DepartementMetierImpl implements IDepartementMetier{
@Autowired
private DepartementRepository departementRepository;
	@Override
	public Departement creer(Departement entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return departementRepository.save(entity);
	}

	@Override
	public Departement modifier(Departement entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return departementRepository.save(entity);
	}

	@Override
	public List<Departement> findAll() {
		// TODO Auto-generated method stub
		return departementRepository.findAll();
	}

	@Override
	public Departement findById(Long id) {
		// TODO Auto-generated method stub
		return departementRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		departementRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Departement> entites) {
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
