package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ci.gstoreplus.entity.shared.AbstractEntity;

@Entity
public class Articles extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	private String code;
	private String nom;
	private String description;
	private String promo;
	private String nouveau;

	@Column(name = "prix_unitaire")
	private double prixUnitaire;
	private String pathImage;
	@ManyToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_produits", nullable = false)
	private Produits produits;
	// clé étrangère
	@Column(name = "id_produits", insertable = false, updatable = false)
	private long idProduits;

	public Articles() {
		super();
	}

	public Articles(String nom, String description, double prixUnitaire) {
		super();
		this.nom = nom;
		this.description = description;
		this.prixUnitaire = prixUnitaire;
		
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public String getNouveau() {
		return nouveau;
	}

	public void setNouveau(String nouveau) {
		this.nouveau = nouveau;
	}

	public String getNom() {
		return nom;
	}

	public String getDescription() {
		return description;
	}

	public double getPrixUnitaire() {
		return prixUnitaire;
	}

	

	public Produits getProduits() {
		return produits;
	}

	public long getIdProduits() {
		return idProduits;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrixUnitaire(double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	
	public void setProduits(Produits produits) {
		this.produits = produits;
	}

	public String getPathImage() {
		return pathImage;
	}

	public void setPathImage(String pathImage) {
		this.pathImage = pathImage;
	}

	@Override
	public String toString() {
		return "Articles [code=" + code + ", nom=" + nom + ", description=" + description + ", prixUnitaire="
				+ prixUnitaire + ", produits=" + produits + ", idProduits=" + idProduits + "]";
	}

	

}
