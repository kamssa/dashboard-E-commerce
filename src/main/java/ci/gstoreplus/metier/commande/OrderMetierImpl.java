package ci.gstoreplus.metier.commande;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ci.gstoreplus.dao.catalogue.ArticleRepository;
import ci.gstoreplus.dao.catalogue.ClientRepository;
import ci.gstoreplus.dao.catalogue.CommandeRepository;
import ci.gstoreplus.dao.catalogue.LigneCommandeRepository;
import ci.gstoreplus.entity.catalogue.Articles;
import ci.gstoreplus.entity.client.Client;
import ci.gstoreplus.entity.commande.Commande;
import ci.gstoreplus.entity.commande.LigneDeCommande;
import ci.gstoreplus.entity.shared.Personne;
import ci.gstoreplus.exception.InvalideGstoreException;

public class OrderMetierImpl implements IOrderMetier{
@Autowired
private CommandeRepository commandeRepository;
@Autowired
private ClientRepository clientRepository;
@Autowired
private ArticleRepository articleRepository;
@Autowired
private LigneCommandeRepository ligneCommandeRepository;
	@Override
	public Commande creer(Personne client, OrderForm orderForm) throws InvalideGstoreException {
        
        client = clientRepository.findById(client.getId()).get();
        System.out.println(client.getId());

        Commande order=new Commande();
        order.setPersonne(client);
        order.setDate(new Date());
        order=commandeRepository.save(order);
        double total=0;
        for(OrderProduct p:orderForm.getProducts()){
            LigneDeCommande orderItem=new LigneDeCommande();
            orderItem.setCommande(order);
            Articles articles=articleRepository.findById(p.getId()).get();
            orderItem.setArticles(articles);
            orderItem.setPrice(articles.getPrixUnitaire());
            orderItem.setQuantity(p.getQuantity());
            ligneCommandeRepository.save(orderItem);
            total+=p.getQuantity()*articles.getPrixUnitaire();
        }
        order.setTotalAmount(total);
        return commandeRepository.save(order);
	}

	@Override
	public Commande modifier(Commande entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return commandeRepository.save(entity);
	}

	@Override
	public List<Commande> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commande findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supprimer(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean supprimer(List<Commande> entites) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existe(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean existsByPseudo(String pseudo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Commande creer(Commande entity) throws InvalideGstoreException {
		// TODO Auto-generated method stub
		return null;
	}

}
