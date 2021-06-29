package ci.gstoreplus.metier.commande;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.catalogue.PaiementRepository;
import ci.gstoreplus.entity.commande.Paiement;
import ci.gstoreplus.exception.InvalideGstoreException;


@Service
public class PaiementMetierImpl implements IPaiementMetier {

	@Autowired
	PaiementRepository paiementRepository;
	@Override
	public Paiement creer(Paiement entity) throws InvalideGstoreException {
		
		return paiementRepository.save(entity);
	}

	@Override
	public Paiement modifier(Paiement modif) throws InvalideGstoreException {
		Optional<Paiement> paiement = paiementRepository.findById(modif.getId());

		if (paiement.isPresent()) {
			
			if (paiement.get().getVersion() != modif.getVersion()) {
				throw new InvalideGstoreException("ce libelle a deja ete modifier");
			}

		} else
			throw new InvalideGstoreException("modif est un objet null");
		
		return paiementRepository.save(modif);
	}

	@Override
	public List<Paiement> findAll() {
		return paiementRepository.findAll();
	}

	@Override
	public Paiement findById(Long id) {
		
		return paiementRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		paiementRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Paiement> entites) {
		paiementRepository.deleteAll(entites);
		return true;
	}

	@Override
	public boolean existe(Long id) {
		paiementRepository.existsById(id);
		return true;
	}

	@Override
	public Paiement getPaiementDeCommande(Long id) {
		return paiementRepository.getPaiementDeCommande(id);
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
