package ci.gstoreplus.metier.admin;

import ci.gstoreplus.entity.shared.Personne;
import ci.gstoreplus.metiers.Imetier;

public interface PersonneMetier  extends Imetier<Personne, Long>{
	void createVerificationToken(Personne personne, String token);
	 Personne getPersonne(String verificationToken);
	 String validateVerificationToken(String token);
	 Personne findByEmail(String email);
}
