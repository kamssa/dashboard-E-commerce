package ci.gstoreplus.entity.shared;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import ci.gstoreplus.models.DateAudit;

@MappedSuperclass
public class AbstractEntity extends DateAudit {
	private static final long serialVersionUID = 1L;
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 protected Long id;
	 @Version
	 protected Long version;
	
	 @Override
	 public int hashCode() {
	 int hash = 0;
	 hash += (id != null ? id.hashCode() : 0);
	 return hash;
	 }
	
	 // initialisation
	 public AbstractEntity build(Long id, Long version) {
	 this.id = id;
	 this.version = version;
	 return this;
	 }
	
	 @Override
	 public boolean equals(Object entity) {
	 String class1 = this.getClass().getName();
	 String class2 = entity.getClass().getName();
	 if (!class2.equals(class1)) {
	 return false;
	 }
	 AbstractEntity other = (AbstractEntity) entity;
	 return this.id == other.id;
	 }

	public Long getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}
	
	 
}
