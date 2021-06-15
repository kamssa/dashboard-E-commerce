package ci.gstoreplus.entity.commande;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ci.gstoreplus.entity.client.Client;
import ci.gstoreplus.entity.shared.AbstractEntity;

@Entity
public class Commande extends AbstractEntity {

	
	private static final long serialVersionUID = 1L;
    private Date date;
    @OneToMany(mappedBy = "commande")
    private Collection<LigneDeCommande> ligneDeCommande;
    @ManyToOne
    private Client client;
    private double totalAmount;
    @OneToOne
    private Paiement payment;
	public Commande() {
		super();
	}
	public Commande(Date date, Collection<LigneDeCommande> ligneDeCommande, Client client, double totalAmount,
			Paiement payment) {
		super();
		this.date = date;
		this.ligneDeCommande = ligneDeCommande;
		this.client = client;
		this.totalAmount = totalAmount;
		this.payment = payment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Collection<LigneDeCommande> getLigneDeCommande() {
		return ligneDeCommande;
	}
	public void setLigneDeCommande(Collection<LigneDeCommande> ligneDeCommande) {
		this.ligneDeCommande = ligneDeCommande;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Paiement getPayment() {
		return payment;
	}
	public void setPayment(Paiement payment) {
		this.payment = payment;
	}

}
