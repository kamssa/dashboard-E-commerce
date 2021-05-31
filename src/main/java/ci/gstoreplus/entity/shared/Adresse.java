package ci.gstoreplus.entity.shared;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {
	private String boitePostal;
    private String mail;
	private String pays;
	private String ville;
	private String siteWeb;
	
	public String getBoitePostal() {
		return boitePostal;
	}
	
	public Adresse() {
		super();
	}

public Adresse(String boitePostal, String mail, String pays, String ville, String siteWeb) {
		super();
		this.boitePostal = boitePostal;
		this.mail = mail;
		this.pays = pays;
		this.ville = ville;
		this.siteWeb = siteWeb;
		
	}

	public void setBoitePostal(String boitePostal) {
		this.boitePostal = boitePostal;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public String getSiteWeb() {
		return siteWeb;
	}
	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}
	
}
