package ci.gstoreplus.entity.commande;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

import ci.gstoreplus.entity.shared.AbstractEntity;

@Entity
public class Paiement extends AbstractEntity{
	 
	private static final long serialVersionUID = 1L;
	private Date datePayment;
	    private long cardNumber;
	    private String cardType;
	    @OneToOne(mappedBy = "payment")
	    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	    private Commande commande;
		public Paiement() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Paiement(Date datePayment, long cardNumber, String cardType, Commande commande) {
			super();
			this.datePayment = datePayment;
			this.cardNumber = cardNumber;
			this.cardType = cardType;
			this.commande = commande;
		}
		public Date getDatePayment() {
			return datePayment;
		}
		public void setDatePayment(Date datePayment) {
			this.datePayment = datePayment;
		}
		public long getCardNumber() {
			return cardNumber;
		}
		public void setCardNumber(long cardNumber) {
			this.cardNumber = cardNumber;
		}
		public String getCardType() {
			return cardType;
		}
		public void setCardType(String cardType) {
			this.cardType = cardType;
		}
		public Commande getCommande() {
			return commande;
		}
		public void setCommande(Commande commande) {
			this.commande = commande;
		}
		@Override
		public String toString() {
			return "Paiement [datePayment=" + datePayment + ", cardNumber=" + cardNumber + ", cardType=" + cardType
					+ ", commande=" + commande + "]";
		}
	    
}
