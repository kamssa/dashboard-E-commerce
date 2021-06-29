package ci.gstoreplus.entity.shared;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ci.gstoreplus.entity.admin.Admin;
import ci.gstoreplus.entity.admin.Employe;
import ci.gstoreplus.entity.client.Client;

@Entity

@Table(name = "T_Personnes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "email"
        })
})
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE_PERSONNE", discriminatorType = DiscriminatorType.STRING, length = 2)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ 
	         @Type(name = "AD", value = Admin.class),
	         @Type(name = "EM", value = Employe.class),
	     	 @Type(name = "CL", value = Client.class) 
	         })
public class Personne extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String titre;
    private String nom;
	private String prenom;
	@NaturalId
    @NotBlank
    @Email
	private String email;
	private String codePays;
	@Column(unique= true)
	private String telephone;
	private String password;
	private String nomComplet;
	private boolean actived;
	@Embedded
	private Adresse adresse;
	@Column(name = "TYPE_PERSONNE", insertable = false, updatable = false)
	private String type;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<Role>();

	public Personne() {
		super();

	}

	public Personne(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	

	public Personne(String titre, String nom, String prenom, @NotBlank @Email String email, String password) {
		super();
		this.titre = titre;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
	}

	public Personne(String titre, String nom, String prenom, @NotBlank @Email String email, String codePays,
			String telephone, String password, String nomComplet, boolean actived, Adresse adresse, String type,
			Set<Role> roles) {
		super();
		this.titre = titre;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.codePays = codePays;
		this.telephone = telephone;
		this.password = password;
		this.nomComplet = nomComplet;
		this.actived = actived;
		this.adresse = adresse;
		this.type = type;
		this.roles = roles;
	}

	public Personne(String nom, String prenom, String email, String telephone, String password, Set<Role> roles) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.password = password;
		this.roles = roles;
	}
	
	
	@PrePersist
	public void setNomComplet() {
		this.nomComplet = nom + " " + prenom;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitre() {
		return titre;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getEmail() {
		return email;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public String getCodePays() {
		return codePays;
	}

	public void setCodePays(String codePays) {
		this.codePays = codePays;
	}

	public String getTelephone() {
		return telephone;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	
	public String getNomComplet() {
		return nomComplet;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public String getType() {
		return type;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}



	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (actived ? 1231 : 1237);
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((codePays == null) ? 0 : codePays.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((nomComplet == null) ? 0 : nomComplet.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (actived != other.actived)
			return false;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (codePays == null) {
			if (other.codePays != null)
				return false;
		} else if (!codePays.equals(other.codePays))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (nomComplet == null) {
			if (other.nomComplet != null)
				return false;
		} else if (!nomComplet.equals(other.nomComplet))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Personne [titre=" + titre + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", telephone="
				+ telephone + ", password=" + password + ", nomComplet=" + nomComplet + ", actived=" + actived
				+ ", adresse=" + adresse + ", type=" + type + ", roles=" + roles + "]";
	}


}
