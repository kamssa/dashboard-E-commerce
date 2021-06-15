package ci.gstoreplus.entity.catalogue;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ci.gstoreplus.entity.shared.AbstractEntity;

@Entity
public class SousCategories extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	private String nom;
    public SousCategories() {
}
	

	public SousCategories(String nom) {
		super();
		this.nom = nom;
		
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	@Override
	public String toString() {
		return "SousCategories [nom=" + nom + "]";
	}
   
	
}