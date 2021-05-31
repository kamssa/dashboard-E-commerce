package ci.gstoreplus.entity.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import ci.gstoreplus.entity.shared.AbstractEntity;
import ci.gstoreplus.models.DateAudit;

@Entity
public class Departement extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	private String libelle;
	private String description;
	
	
	public Departement() {
		super();
	}
	public Departement(String libelle, String description) {
		super();
		this.libelle = libelle;
		this.description = description;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public Long getVersion() {
		return version;
	}
	@Override
	public String toString() {
		return String.format("Departement[%s,%s]",  libelle, description);
	}
	
}
