package ci.gstoreplus.listener;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import ci.gstoreplus.entity.shared.Personne;
import ci.gstoreplus.metier.catalogue.ClientMetier;
import ci.gstoreplus.models.ContenuMail;




@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	@Autowired
	private ClientMetier service;
	

	@Autowired
	private MessageSource messages;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private Environment env;
	@Value("${support.email}")
	private String setFrom;

	@Override
	@Async
	public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
		try {
			this.confirmRegistration(event);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Async
	private void confirmRegistration(final OnRegistrationCompleteEvent event)
			throws MessagingException, NoSuchAlgorithmException {

		final Personne personne = event.getPersonne();
		// final Demande t = serviceDemande.(personne.getId());
		final String action = event.getAppUrl();
		String token = "";
		String tokenHtml = "";
		String token1 = "";
		String lien = "https://immo.gstoreplus.ci/#/verification?login=" +
		personne.getEmail() + "&key="
				+ UUID.randomUUID().toString();

		if (action.equals("Register")) {
			for (int i = 0; i < 6; i++) {
				token1 = (int) (Math.random() * (10)) + "";
				token += token1;
				tokenHtml += "<div style=\"width:10%; padding:10px; "
						+ " border:1px dashed red; float:left;\">" + token1
						+ "</div>";

			}

			ContenuMail mail = new ContenuMail();
			mail.setCodeValidation(tokenHtml);
			// String codeValidation= mail.getCodeValidation();
			String contentHtml = mail.getContenuHtml(tokenHtml, lien + "&code=" + token);
			service.createVerificationToken(personne, token);

			constructEmailMessage(event, personne, token, contentHtml);
			//constructSmsMessage(event, personne, token);
			
		} 
		else if (action.equals("Reset")) {
			String mdpOublier = "123456789";
			String tokenMdpOublier = null;
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(mdpOublier.getBytes());
			byte byteData[] = md.digest();
			// convertir le tableau de bits en une format hexadécimal
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				String hex = Integer.toHexString(0xff & byteData[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			tokenMdpOublier = hexString.toString();
			lien = "http://weget:4200/#/checking/" + personne.getEmail() + "/" + tokenMdpOublier + "/"
					+ UUID.randomUUID().toString();
			ContenuMail mail = new ContenuMail();
			// mail.setCodeValidation(tokenHtml);
			System.out.println("le lien !" + lien);

			String contentHtml = mail.getContenuHtml(tokenHtml, lien + "/" + token);
			// System.out.println("le lien !"+lien);
			System.out.println("le lien !" + tokenMdpOublier);
			service.createVerificationToken(personne, tokenMdpOublier);
			constructEmailMessage(event, personne, tokenMdpOublier, contentHtml);
		} else
			System.out.println("le mail n'est pas parti !");

	}

	// mailSender.send(email);

	@Async
	private final void constructEmailMessage(final OnRegistrationCompleteEvent event, final Personne personne,
			final String token, String contHtml) throws MessagingException {
		// ContenuMail contenu = new ContenuMail();
		final String recipientAddress = personne.getEmail();
		System.out.println(recipientAddress);
		final String subject = "Registration Confirmation";
		final String confirmationUrl = contHtml;
		// final String codeValidation1 = codeValidation;

		// final String message = messages.getMessage("impossible de continue", null,
		// event.getLocale());

		MimeMessage email = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(email, true, "UTF-8");

		// final String message = messages.getMessage("impossible de continue", null,
		// event.getLocale());

		helper.setFrom(setFrom);
		helper.setTo(recipientAddress);
		helper.setSubject(subject);
		helper.setText(confirmationUrl, true);
		// email.setFrom(env.getProperty("support.email"));
		mailSender.send(email);

		System.out.println("++++++++++++++++++++++reponse email++++++++++++++++++++" + "OK");
}

	private final void constructSmsMessage(final OnRegistrationCompleteEvent event, final Personne personne,
			final String token) throws MessagingException {
		String url = "http://africasmshub.mondialsms.net/api/api_http.php";
		String token2 = token.substring(0, 3);
		// String token3=token.substring(3, 6);
		String token3 = token.substring(token.length() - 3);
		String token1 = String.format("%s", token);
		final String confirmationSms = "Salut, votre inscription a bien été prise en compte. CODE DE CONFIRMATION : "
				+ token2 + "-" + token3;
		final String sms = confirmationSms + ".Merci de nous faire confiance.";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<Object, Object> map = new LinkedMultiValueMap<Object, Object>();
		map.add("username", "PREPA");
		map.add("password", "PREPA1TOGET");
		map.add("sender", "TOGETOP");
		map.add("text", sms);
		map.add("type", "text");
		map.add("date", LocalDateTime.now().toString());
		map.add("to", personne.getTelephone());

		HttpEntity<MultiValueMap<Object, Object>> request = new HttpEntity<MultiValueMap<Object, Object>>(map, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		System.out.println("++++++++++++++++++++++reponse sms++++++++++++++++++++" + response.getBody());

	}

	public String getSetFrom() {
		return setFrom;
	}

	public void setSetFrom(String setFrom) {
		this.setFrom = setFrom;
	}

}
