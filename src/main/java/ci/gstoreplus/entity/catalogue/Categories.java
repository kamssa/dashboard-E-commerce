package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.shared.AbstractEntity;

@Entity
public class Categories extends AbstractEntity {
	
	private static final long serialVersionUID = 7716507459244162067L;
	private String nom;
	private String description;
	public Categories() {
		super();
	}
	public Categories(String nom, String description) {
		super();
		this.nom = nom;
		this.description = description;
		}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	
	public Long getVersion() {
		return version;
	}
	@Override
	public String toString() {
		return "Categories [nom=" + nom + ", description=" + description + "]";
	}

}
