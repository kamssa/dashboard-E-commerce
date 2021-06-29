package ci.gstoreplus.entity.catalogue;



import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import ci.gstoreplus.entity.shared.AbstractEntity;

@Entity
public class SousCategories extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	private String nom;
	@ManyToOne(fetch = FetchType.EAGER, cascade =CascadeType.MERGE)
	@JoinColumn(name = "fk_Categorie", nullable = false)
	private Categories categories;
    public SousCategories() {
}
	

	public SousCategories(String nom) {
		super();
		this.nom = nom;
		
	}


	public SousCategories(String nom, Categories categories) {
		super();
		this.nom = nom;
		this.categories = categories;
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	public Categories getCategories() {
		return categories;
	}


	public void setCategories(Categories categories) {
		this.categories = categories;
	}


	@Override
	public String toString() {
		return "SousCategories [nom=" + nom + ", categories=" + categories + "]";
	}


	
	
}