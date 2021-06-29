package ci.gstoreplus.metier.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ci.gstoreplus.dao.admin.PersonneRepository;
import ci.gstoreplus.entity.shared.Personne;
import ci.gstoreplus.exception.InvalideGstoreException;

@Service
public class PersonneMetierImpl implements PersonneMetier{
@Autowired
private PersonneRepository personneRepository;
@Autowired
PasswordEncoder passwordEncoder;
	@Override
	public Personne creer(Personne p) throws InvalideGstoreException {
		System.out.println("admin a enregistrer" + ":" + p);
		if ((p.getEmail().equals(null)) || (p.getEmail() == "")) {
			throw new InvalideGstoreException("L'email ne peut etre null");
		}
		
		Optional<Personne> pers = null;

		pers = personneRepository.findByEmail(p.getEmail());
		if (pers.isPresent()) {
			throw new InvalideGstoreException("Cet email est deja utilisé");
		}

		p.setPassword(passwordEncoder.encode(p.getPassword()));
		
		return personneRepository.save(p);
		
	}

	@Override
	public Personne modifier(Personne entity) throws InvalideGstoreException {
		return personneRepository.save(entity);
	}

	@Override
	public List<Personne> findAll() {
		return personneRepository.findAll();
	}

	@Override
	public Personne findById(Long id) {
		return personneRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		personneRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean supprimer(List<Personne> entites) {
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
		return personneRepository.existsByEmail(email);
	}

	@Override
	public void createVerificationToken(Personne personne, String token) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Personne getPersonne(String verificationToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String validateVerificationToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personne findByEmail(String email) {
		// TODO Auto-generated method stub
		return personneRepository.findByEmail(email).get();
	}

}
