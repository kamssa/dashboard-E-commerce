package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;

import ci.gstoreplus.entity.shared.AbstractEntity;

@Entity
public class ImageDetail extends AbstractEntity{

	
	private static final long serialVersionUID = 1L;
	private Long idDetailArticle;
	private String imageUrl;
	private String imageId;
	
	public ImageDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImageDetail(Long idDetailArticle, String imageUrl, String imageId) {
		super();
		this.idDetailArticle = idDetailArticle;
		this.imageUrl = imageUrl;
		this.imageId = imageId;
	}

	public Long getIdDetailArticle() {
		return idDetailArticle;
	}

	public void setIdDetailArticle(Long idDetailArticle) {
		this.idDetailArticle = idDetailArticle;
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
