package ci.gstoreplus.listener;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import ci.gstoreplus.entity.shared.Personne;





public class OnRegistrationCompleteEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;
	    private final String appUrl;
	     private final Locale locale;
	    private final Personne personne;

	    public  OnRegistrationCompleteEvent(final Personne personne, final Locale locale, 
			final String appUrl) {
		super(personne);
		this.personne = personne;
		this.locale = locale;
		this.appUrl = appUrl;

	}

	public String getAppUrl() {
		return appUrl;
	}

	public Locale getLocale() {
		return locale;
	}

	public Personne getPersonne() {
		return personne;
	}

	
}
