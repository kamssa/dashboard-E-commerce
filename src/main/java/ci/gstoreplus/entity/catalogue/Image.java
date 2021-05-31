package ci.gstoreplus.entity.catalogue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import ci.gstoreplus.models.DateAudit;



@Entity
public class Image extends DateAudit{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private Long version;
	private Long idArticles;
    private String path;


public Image() {
	super();
}

public Image(String path) {
	super();
	this.path = path;
}

public String getPath() {
	return path;
}

public void setPath(String path) {
	this.path = path;
}

public Long getId() {
	return id;
}

public Long getVersion() {
	return version;
}

public Long getIdArticles() {
	return idArticles;
}

public void setIdArticles(Long idArticles) {
	this.idArticles = idArticles;
}

@Override
public String toString() {
	return "Image [id=" + id + ", version=" + version + ", idArticles=" + idArticles + ", path=" + path + "]";
}


}
