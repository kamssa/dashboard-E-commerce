package ci.gstoreplus.metier.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.admin.RegionRepository;
import ci.gstoreplus.entity.client.Region;
import ci.gstoreplus.exception.InvalideGstoreException;

@Service
public class RegionMetierImpl  implements IRegionMetier{
@Autowired
private RegionRepository regionRepository;
	@Override
	public Region creer(Region entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return regionRepository.save(entity);
	}

	@Override
	public Region modifier(Region entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return regionRepository.save(entity);
	}

	@Override
	public List<Region> findAll() {
		// TODO Auto-generated method stub
		return regionRepository.findAll();
	}

	@Override
	public Region findById(Long id) {
		// TODO Auto-generated method stub
		return regionRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(List<Region> entites) {
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
