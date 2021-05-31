package ci.gstoreplus.entity.client;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ci.gstoreplus.entity.shared.Personne;

@Entity
@DiscriminatorValue("CL")
public class Client extends Personne{

	
	private static final long serialVersionUID = 1L;

	public Client() {
		super();
	}

	public Client(String titre, String nom, String prenom, String email, String password) {
		super(titre, nom, prenom, email, password);
	}
	@Override
	public String toString() {
		return String.format("Client[%s]", super.toString());
	}

}
