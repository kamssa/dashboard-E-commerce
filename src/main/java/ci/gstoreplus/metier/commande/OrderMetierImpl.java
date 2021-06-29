package ci.gstoreplus.metier.commande;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ci.gstoreplus.dao.admin.PersonneRepository;
import ci.gstoreplus.dao.catalogue.ArticleRepository;
import ci.gstoreplus.dao.catalogue.CommandeRepository;
import ci.gstoreplus.dao.catalogue.LigneCommandeRepository;
import ci.gstoreplus.entity.catalogue.Articles;
import ci.gstoreplus.entity.commande.Commande;
import ci.gstoreplus.entity.commande.LigneDeCommande;
import ci.gstoreplus.entity.shared.Personne;
import ci.gstoreplus.exception.InvalideGstoreException;

@Service
public class OrderMetierImpl implements IOrderMetier{
@Autowired
private CommandeRepository commandeRepository;
@Autowired
private PersonneRepository clientRepository;
@Autowired
private ArticleRepository articleRepository;
@Autowired
private LigneCommandeRepository ligneCommandeRepository;
    
    @Transactional
	@Override
	public Commande creer(OrderForm orderForm) throws InvalideGstoreException {
        System.out.println("Voir la commande1:" + orderForm);
        Personne client = orderForm.getClient();
        Personne p = clientRepository.findByEmail(client.getEmail()).get();
        System.out.println("Voir orderForm.getClient() de la base: "+ p);
        Commande order = new Commande();
        System.out.println("Voir order################: "+ order);

        order.setPersonne(p);
        order.setTotalAmount(0D);
        order=commandeRepository.save(order);
        System.out.println("Voir order&&&&&&&&&&&: "+ order);

        double total=0;
        for(OrderArticle a: orderForm.getArticles()){
            LigneDeCommande orderItem=new LigneDeCommande();
            orderItem.setCommande(order);
            Articles articles=articleRepository.findById(a.getId()).get();
            System.out.println("Voir article &&&&&&&&&&&: "+ articles);
            orderItem.setArticles(articles);
            orderItem.setPrice(a.getPrice());
            orderItem.setQuantity(a.getQuantity());
            orderItem.setTotal(a.getPrice()*a.getQuantity());
            ligneCommandeRepository.save(orderItem);
            total+=a.getQuantity()*a.getPrice();
            System.out.println("Voir total &&&&&&&&&&&: "+ total);

        }
        order.setTotalAmount(total);
        System.out.println("Voir order et total &&&&&&&&&&&: "+ order);

        Commande c = commandeRepository.save(order);
        System.out.println("Voir c: "+ c);
        return c;
	}

	@Override
	public Commande modifier(Commande entity) throws InvalideGstoreException {
		return commandeRepository.save(entity);
	}

	@Override
	public List<Commande> findAll() {
		return commandeRepository.findAll();
	}

	@Override
	public Commande findById(Long id) {
		return commandeRepository.findById(id).get();
	}

	@Override
	public boolean supprimer(Long id) {
		commandeRepository.deleteById(id);
		return true;
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
