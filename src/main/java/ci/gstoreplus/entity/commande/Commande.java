package ci.gstoreplus.entity.commande;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import ci.gstoreplus.entity.shared.AbstractEntity;
import ci.gstoreplus.entity.shared.Personne;

@Entity
public class Commande extends AbstractEntity {

	private static final long serialVersionUID = 1L;
    private boolean paye;
	private LocalDateTime date;
    private String motif;
	private Double totalAmount;
	private String numero;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_Personne")
	private Personne personne;
	

	public Commande() {
		super();
	}

	

	public Commande(LocalDateTime date, Double totalAmount) {
		super();
		this.date = date;
		this.totalAmount = totalAmount;
	}

  public Commande(boolean paye, LocalDateTime date, String motif, Double totalAmount, String numero,
			Personne personne) {
		super();
		this.paye = paye;
		this.date = date;
		this.motif = motif;
		this.totalAmount = totalAmount;
		this.numero = numero;
		this.personne = personne;
	}



	@PrePersist
	@PreUpdate
	public void setDate() {
		this.date = LocalDateTime.now();
	}

	
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

		public boolean isPaye() {
		return paye;
	}

	public void setPaye(boolean paye) {
		this.paye = paye;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}



	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((motif == null) ? 0 : motif.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + (paye ? 1231 : 1237);
		result = prime * result + ((personne == null) ? 0 : personne.hashCode());
		result = prime * result + ((totalAmount == null) ? 0 : totalAmount.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (motif == null) {
			if (other.motif != null)
				return false;
		} else if (!motif.equals(other.motif))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (paye != other.paye)
			return false;
		if (personne == null) {
			if (other.personne != null)
				return false;
		} else if (!personne.equals(other.personne))
			return false;
		if (totalAmount == null) {
			if (other.totalAmount != null)
				return false;
		} else if (!totalAmount.equals(other.totalAmount))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Commande [paye=" + paye + ", date=" + date + ", motif=" + motif + ", totalAmount=" + totalAmount
				+ ", numero=" + numero + ", personne=" + personne + "]";
	}


}
