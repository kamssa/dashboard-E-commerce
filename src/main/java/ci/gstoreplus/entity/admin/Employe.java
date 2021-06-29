package ci.gstoreplus.entity.admin;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import ci.gstoreplus.entity.shared.Adresse;
import ci.gstoreplus.entity.shared.Personne;
import ci.gstoreplus.entity.shared.Role;


@Entity
@DiscriminatorValue("EM")
public class Employe extends Personne{

	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name= "id_departement")
	private Departement departement;
	// clé étrangère
	 @Column(name = "id_departement", insertable = false, updatable = false)
	 private long idDepartement;
	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employe(String nom, String prenom, String email, String telephone, String password, Set<Role> roles) {
		super(nom, prenom, email, telephone, password, roles);
		// TODO Auto-generated constructor stub
	}

	public Employe(String titre, String nom, String prenom, @NotBlank @Email String email, String codePays,
			String telephone, String password, String nomComplet, boolean actived, Adresse adresse, String type,
			Set<Role> roles) {
		super(titre, nom, prenom, email, codePays, telephone, password, nomComplet, actived, adresse, type, roles);
		// TODO Auto-generated constructor stub
	}

	public Employe(String email, String password) {
		super(email, password);
		// TODO Auto-generated constructor stub
	}

	public Departement getDepartement() {
		return departement;
	}
	public long getIdDepartement() {
		return idDepartement;
	}
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	@Override
	public String toString() {
		return String.format("Admin[%s]", super.toString());
	}

}
