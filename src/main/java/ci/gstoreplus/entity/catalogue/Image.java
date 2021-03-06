package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;
import ci.gstoreplus.entity.shared.AbstractEntity;

@Entity
public class Image extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	private Long idArticles;
	private String nom;
	private String imageUrl;
	private String imageId;

	public Image() {
		super();
	}

	public Long getIdArticles() {
		return idArticles;
	}

	public void setIdArticles(Long idArticles) {
		this.idArticles = idArticles;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

}
