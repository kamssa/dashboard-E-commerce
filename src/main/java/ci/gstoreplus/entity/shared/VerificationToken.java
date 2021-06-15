package ci.gstoreplus.entity.shared;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "T_VerificationToken")
public class VerificationToken extends AbstractEntity {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String token;

	@OneToOne(targetEntity = Personne.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "personne_id")
	private Personne personne;

	private LocalDateTime expiryDate = LocalDateTime.now().plusDays(1);

	public VerificationToken() {
		super();

	}

	public VerificationToken(String token, Personne personne) {
		super();
		this.token = token;
		this.personne = personne;
	}

	public VerificationToken(String token, Personne personne, LocalDateTime expiryDate) {
		super();
		this.token = token;
		this.personne = personne;
		this.expiryDate = expiryDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	 public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	public void updateToken(final String token) {
	        this.token = token;
	        this.expiryDate = LocalDateTime.now().plusDays(1);
	    }
}
