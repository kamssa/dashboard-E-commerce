package ci.gstoreplus.entity.commande;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ci.gstoreplus.entity.client.Client;
import ci.gstoreplus.entity.shared.AbstractEntity;
import ci.gstoreplus.entity.shared.Personne;

@Entity
public class Commande extends AbstractEntity {

	
	private static final long serialVersionUID = 1L;
    private Date date;
    @OneToMany(mappedBy = "commande")
    private Collection<LigneDeCommande> ligneDeCommande;
    @ManyToOne
    private Personne personne;
    private double totalAmount;
    @OneToOne
    private Paiement payment;
	public Commande() {
		super();
	}
	public Commande(Date date, Collection<LigneDeCommande> ligneDeCommande, Personne personne, double totalAmount,
			Paiement payment) {
		super();
		this.date = date;
		this.ligneDeCommande = ligneDeCommande;
		this.personne = personne;
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
	
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
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
