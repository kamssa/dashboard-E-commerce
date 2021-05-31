package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	@JoinColumn(name = "id_categories", nullable = false)
	private Categories categories;

	// clé étrangère
	@Column(name = "id_categories", insertable = false, updatable = false)
	private long idCategories;

	public Produits() {
		super();
	}

	public Produits(String nom, String description, Categories categories) {
		super();
		this.nom = nom;
		this.description = description;
		this.categories = categories;

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

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public Long getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}

	public long getIdCategories() {
		return idCategories;
	}

	@Override
	public String toString() {
		return "Produits [nom=" + nom + ", description=" + description + ", categories=" + categories
				+ ", idCategories=" + idCategories + "]";
	}

}
