package ci.gstoreplus.metiers;

import java.util.List;

import ci.gstoreplus.exception.InvalideGstoreException;





public interface Imetier <T,U>{
	
	public T creer(T entity) throws InvalideGstoreException;
	
	public T modifier(T entity) throws InvalideGstoreException;
	
	public List<T> findAll();
	
	public T findById(U id);

	public boolean supprimer(U id);
	
	public boolean supprimer(List<T> entites);
	
	public boolean existe(U id);
	
	Boolean existsByPseudo(String pseudo);

	Boolean existsByEmail(String email);

}
