package ci.gstoreplus.entity.client;

import javax.persistence.Entity;

import ci.gstoreplus.entity.shared.AbstractEntity;

@Entity
public class Ville extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	
	private String nom;
	private String description;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Ville [nom=" + nom + ", description=" + description + "]";
	}
	
}
