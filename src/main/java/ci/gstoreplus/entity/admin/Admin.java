package ci.gstoreplus.entity.admin;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import ci.gstoreplus.entity.shared.Personne;

@Entity
@DiscriminatorValue("AD")
public class Admin extends Personne {

	
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}

	public Admin(String titre, String nom, String prenom, String email, String password) {
		super(titre, nom, prenom, email, password);
	}

	@Override
	public String toString() {
		return String.format("Admin[%s]", super.toString());

	}

}