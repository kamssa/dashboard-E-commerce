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
@JsonSubTypes({ @Type(name = "AD", value = Admin.class), @Type(name = "EM", value = Employe.class),
		@Type(name = "Cl", value = Client.class) })
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
	private Set<Role> roles = new HashSet<>();

	public Personne() {
		super();

	}

	public Personne(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Personne(String titre, String nom, String prenom, String email, String password) {
		super();
		this.titre = titre;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
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
	public String toString() {
		return "Personne [titre=" + titre + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", telephone="
				+ telephone + ", password=" + password + ", nomComplet=" + nomComplet + ", actived=" + actived
				+ ", adresse=" + adresse + ", type=" + type + ", roles=" + roles + "]";
	}


}
