package ci.gstoreplus.entity.shared;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {
	private String boitePostal;
    private String situationGeo;
    private String ville;
    private String region;
	private String pays;
	
	public Adresse() {
		super();
	}

	public Adresse(String boitePostal, String situationGeo, String ville, String region, String pays) {
		super();
		this.boitePostal = boitePostal;
		this.situationGeo = situationGeo;
		this.ville = ville;
		this.region = region;
		this.pays = pays;
	}

	public String getBoitePostal() {
		return boitePostal;
	}

	public void setBoitePostal(String boitePostal) {
		this.boitePostal = boitePostal;
	}

	public String getSituationGeo() {
		return situationGeo;
	}

	public void setSituationGeo(String situationGeo) {
		this.situationGeo = situationGeo;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boitePostal == null) ? 0 : boitePostal.hashCode());
		result = prime * result + ((pays == null) ? 0 : pays.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + ((situationGeo == null) ? 0 : situationGeo.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adresse other = (Adresse) obj;
		if (boitePostal == null) {
			if (other.boitePostal != null)
				return false;
		} else if (!boitePostal.equals(other.boitePostal))
			return false;
		if (pays == null) {
			if (other.pays != null)
				return false;
		} else if (!pays.equals(other.pays))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (situationGeo == null) {
			if (other.situationGeo != null)
				return false;
		} else if (!situationGeo.equals(other.situationGeo))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Adresse [boitePostal=" + boitePostal + ", situationGeo=" + situationGeo + ", ville=" + ville
				+ ", region=" + region + ", pays=" + pays + "]";
	}

	
	

}
