package ci.gstoreplus.entity.catalogue;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ci.gstoreplus.entity.shared.AbstractEntity;

@Entity
public class Produits extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	private String nom;
	private String description;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "fk_SousCategories", nullable = false)
	private SousCategories sousCategories;
	public Produits() {
		super();
	}
  public Produits(String nom, String description, SousCategories sousCategories) {
		super();
		this.nom = nom;
		this.description = description;
		this.sousCategories = sousCategories;
	}

  public Produits(String nom, String description) {
		super();
		this.nom = nom;
		this.description = description;
	}

public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}


	public SousCategories getSousCategories() {
		return sousCategories;
	}
	public void setSousCategories(SousCategories sousCategories) {
		this.sousCategories = sousCategories;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Produits [nom=" + nom + ", description=" + description + "]";
	}

}
