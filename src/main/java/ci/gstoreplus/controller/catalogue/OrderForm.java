package ci.gstoreplus.controller.catalogue;

import java.util.Date;
import java.util.List;

import ci.gstoreplus.entity.client.Client;



public class OrderForm {
    private Long id;
	private List<OrderArticle> articles;
    private Client client;
    private Date date;
    private double totalAmounth;
	
    
    public OrderForm() {
		super();
	}
    
	
	public OrderForm(List<OrderArticle> articles, Client client, Date date, double totalAmounth) {
		super();
		this.articles = articles;
		this.client = client;
		this.date = date;
		this.totalAmounth = totalAmounth;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public List<OrderArticle> getArticles() {
		return articles;
	}
	public void setArticles(List<OrderArticle> articles) {
		this.articles = articles;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getTotalAmounth() {
		return totalAmounth;
	}
	public void setTotalAmounth(double totalAmounth) {
		this.totalAmounth = totalAmounth;
	}


	@Override
	public String toString() {
		return "OrderForm [id=" + id + ", articles=" + articles + ", client=" + client + ", date=" + date
				+ ", totalAmounth=" + totalAmounth + "]";
	}


	
}

class OrderArticle {
   
	private Long id;
	private String nom;
    private double price;
    private int quantity;
    
	public int getQuantity() {
		return quantity;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderArticle [id=" + id + ", nom=" + nom + ", price=" + price + ", quantity=" + quantity + "]";
	}
	
	
}
