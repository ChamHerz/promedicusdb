package promedicusdb.util;

public class EmailThread extends Thread {
	
	private String emailTo;
	private String UUID;
	private String URL;
	
	public String getEmailTo() {
		return emailTo;
	}
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	
	public EmailThread(String emailTo, String uUID) {
		super();
		this.emailTo = emailTo;
		UUID = uUID;
		this.URL = "http://localhost:4200/register-email/" + UUID;
	}
	@Override
	public void run() {
		//ENVIO DE EMAIL
		SendEmail.send("to",emailTo,
			    "title","Activar Email",
			    "body",String.format(
			    "<h1>%s</h1><a href='%s'>%s</a>",
			    "El siguiente link te permitirá activar tu cuenta",
			    URL,
			    "Link para activar cuenta")
			);	
	}
	
	

}
