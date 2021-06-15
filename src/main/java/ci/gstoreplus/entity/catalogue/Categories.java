package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.shared.AbstractEntity;

@Entity
public class Categories extends AbstractEntity {
	
	private static final long serialVersionUID = 7716507459244162067L;
	private String nom;
	
	public Categories() {
		super();
	}

	public Categories(String nom) {
		super();
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return "Categories [nom=" + nom + "]";
	}
	
	
}
