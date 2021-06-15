package ci.gstoreplus.entity.commande;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import ci.gstoreplus.entity.catalogue.Articles;
import ci.gstoreplus.entity.shared.AbstractEntity;

@Entity
public class LigneDeCommande extends AbstractEntity{

	private static final long serialVersionUID = 1L;
	 	@ManyToOne
	    private Articles articles;
	    private int quantity;
	    private double price;
	    @ManyToOne
	    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	    private Commande commande;
		public LigneDeCommande() {
			super();
		}
		public LigneDeCommande(Articles articles, int quantity, double price, Commande commande) {
			super();
			this.articles = articles;
			this.quantity = quantity;
			this.price = price;
			this.commande = commande;
		}
		public Articles getArticles() {
			return articles;
		}
		public void setArticles(Articles articles) {
			this.articles = articles;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public Commande getCommande() {
			return commande;
		}
		public void setCommande(Commande commande) {
			this.commande = commande;
		}

}
