package ci.gstoreplus;

import java.util.Collections;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.crypto.password.PasswordEncoder;

import ci.gstoreplus.dao.admin.PersonneRepository;
import ci.gstoreplus.dao.admin.RoleRepository;



@SpringBootApplication
@EntityScan(basePackageClasses = { 
		GstoreplusECommerceApplication.class,
		Jsr310JpaConverters.class 
})public class GstoreplusECommerceApplication implements CommandLineRunner {
	
	@Autowired
	PersonneRepository personneRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	public static void main(String[] args) {
		SpringApplication.run(GstoreplusECommerceApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
		/*this.roleRepository.save(new Role(RoleName.ROLE_ADMIN));
		this.roleRepository.save(new Role(RoleName.ROLE_CLIENT));
		this.roleRepository.save(new Role(RoleName.ROLE_EMPLOYE));
		
        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN).get();
		Admin ad = new Admin("Mr", "Traore", "Abdoulaye", "kamssa0@gmail.com", passwordEncoder.encode("Cancer01"));
		ad.setRoles(Collections.singleton(userRole));
         ad = personneRepository.save(ad);*/
		//this.produitRepository.deleteById(53L);
		
	}


}
