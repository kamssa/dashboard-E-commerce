package ci.gstoreplus.entity.catalogue;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import ci.gstoreplus.entity.shared.AbstractEntity;

@Entity
public class Categories extends AbstractEntity {
	
	private static final long serialVersionUID = 7716507459244162067L;
	private String nom;
	@OneToMany(fetch= FetchType.EAGER, cascade= CascadeType.ALL)
	@JoinColumn(name = "fk_Categories")
	private List<SousCategories> SousCategories = new ArrayList<>();
	public Categories() {
		super();
	}
	
	public Categories(String nom, List<SousCategories> sousCategories) {
		super();
		this.nom = nom;
		SousCategories = sousCategories;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public List<SousCategories> getSousCategories() {
		return SousCategories;
	}
	public void setSousCategories(List<SousCategories> sousCategories) {
		SousCategories = sousCategories;
	}

	@Override
	public String toString() {
		return "Categories [nom=" + nom + ", SousCategories=" + SousCategories + "]";
	}
	

}
