package ci.gstoreplus.entity.admin;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import ci.gstoreplus.entity.shared.Adresse;
import ci.gstoreplus.entity.shared.Personne;
import ci.gstoreplus.entity.shared.Role;

@Entity
@DiscriminatorValue("AD")
public class Admin extends Personne {

	
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}

	

	public Admin(String titre, String nom, String prenom, @NotBlank @Email String email, String password) {
		super(titre, nom, prenom, email, password);
		// TODO Auto-generated constructor stub
	}



	public Admin(String nom, String prenom, String email, String telephone, String password, Set<Role> roles) {
		super(nom, prenom, email, telephone, password, roles);
		// TODO Auto-generated constructor stub
	}



	public Admin(String titre, String nom, String prenom, @NotBlank @Email String email, String codePays,
			String telephone, String password, String nomComplet, boolean actived, Adresse adresse, String type,
			Set<Role> roles) {
		super(titre, nom, prenom, email, codePays, telephone, password, nomComplet, actived, adresse, type, roles);
		// TODO Auto-generated constructor stub
	}



	public Admin(String email, String password) {
		super(email, password);
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return String.format("Admin[%s]", super.toString());

	}

}