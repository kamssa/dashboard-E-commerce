package ci.gstoreplus.entity.catalogue;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ci.gstoreplus.entity.shared.AbstractEntity;


@Entity
public class DetailArticles extends AbstractEntity{
	private static final long serialVersionUID = 1L;
	private String couleur;
	private String taille;
	private String marque;
	private String description;
	
	@OneToOne(fetch = FetchType.LAZY, cascade =CascadeType.MERGE)
	@JoinColumn(name = "id_articles", nullable = false)
	private Articles articles;
	// clé étrangère
	@Column(name = "id_articles", insertable = false, updatable = false)
	private long idArticles;
	
	
	public DetailArticles() {
		super();
	}
	
	public DetailArticles(String description, Articles articles) {
		super();
		this.description = description;
		this.articles = articles;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Articles getArticles() {
		return articles;
	}
	public void setArticles(Articles articles) {
		this.articles = articles;
	}
	public long getIdArticles() {
		return idArticles;
	}

	@Override
	public String toString() {
		return "DetailArticles [description=" + description + ", articles=" + articles + ", idArticles=" + idArticles
				+ "]";
	}
	
	}
