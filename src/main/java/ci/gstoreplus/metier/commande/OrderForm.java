package ci.gstoreplus.metier.commande;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import ci.gstoreplus.entity.client.Client;
import ci.gstoreplus.entity.shared.Personne;



public class OrderForm {
    private Personne personne=new Client();
    private List<OrderProduct> products=new ArrayList<>();
	public Personne getPersonne() {
		return personne;
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
	}
	public List<OrderProduct> getProducts() {
		return products;
	}
	public void setProducts(List<OrderProduct> products) {
		this.products = products;
	}
    
}

class OrderProduct{
    private Long id;
    private int quantity;
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getId() {
		return id;
	}

}
