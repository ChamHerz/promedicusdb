package promedicusdb.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	static private Properties mailServerProperties;
	  static private Session getMailSession;
	  static private MimeMessage generateMailMessage;
	  static private String login = "noreplypromedicus@gmail.com";
	  static private String password = "iiss2018";
	  static private String to = null;
	  static private String bcc = "adrielchambers@gmail.com";
	  static private String subject = null;
	  static private String body = null;

	  public static void config(String... namesAndValues){
		  	if (namesAndValues.length % 2 == 1) {
		      throw new IllegalArgumentException("The number of arguments must be pair.");
		  	}
		  	String nameConfig = null, valueConfig = null;
		  	for (int i = 0; i < namesAndValues.length - 1; i += 2) {
		      nameConfig = namesAndValues[i].trim().toLowerCase();
		      valueConfig = namesAndValues[i +1];
		      switch (nameConfig) {
		        case "to":
		        	SendEmail.to = valueConfig;
		          break;
		        case "title":
		        case "subject":
		        	SendEmail.subject = valueConfig;
		          break;
		        case "msg":
		        case "body":
		        	SendEmail.body = valueConfig;
		      }
		  	}
		  }
	  
	  public static Boolean send(String... namesAndValues) {
		    SendEmail.config(namesAndValues);
		    try {
		        return SendEmail.send();
		    } catch (Exception e) {
		        // TODO Auto-generated catch block
		        System.out.println(e);
		    }
		    return false;
		  }
	  
	  public static Boolean send() throws AddressException, MessagingException {
		    Boolean success = false;
		    mailServerProperties = System.getProperties();
		    mailServerProperties.put("mail.smtp.host", "smtp.gmail.com");
		    mailServerProperties.put("mail.smtp.socketFactory.port", "465");
		    mailServerProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		    mailServerProperties.put("mail.smtp.auth", "true");
		    mailServerProperties.put("mail.smtp.port", "465");
		    if (SendEmail.login == null || SendEmail.password == null) {
		    	return success;
		    }
		    getMailSession = Session.getDefaultInstance(mailServerProperties,new javax.mail.Authenticator() {
		    	@Override
		      protected PasswordAuthentication getPasswordAuthentication() {
		    		return new PasswordAuthentication(SendEmail.login, SendEmail.password);
		      }
		    });

		    generateMailMessage = new MimeMessage(getMailSession);
		    if (SendEmail.to != null) {
		        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(SendEmail.to));
		    }
		    if (SendEmail.bcc != null) {
		        generateMailMessage.addRecipient(Message.RecipientType.BCC, new InternetAddress(SendEmail.bcc));
		    }
		    if (SendEmail.subject == null) {
		    	SendEmail.subject = "subject gmail object";
		    }
		    generateMailMessage.setSubject(SendEmail.subject);
		    if (SendEmail.body == null) {
		    	SendEmail.body = "<h1>body gmail object</h1><p>it's a simple test</p>";
		    }
		    generateMailMessage.setContent(SendEmail.body, "text/html");
		    javax.mail.Transport.send(generateMailMessage);
		    return success;
		}
}
