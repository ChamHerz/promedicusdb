package promedicusdb.util;

public class EmailThread extends Thread {
	
	private String emailTo;
	private String UUID;
	private String actionURL;
	private String URL;
	private String urlName;
	private String title;
	private String body;
	
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
	
	public EmailThread(String emailTo,String actionUrl, String uUID, String urlName, String title, String body) {
		super();
		this.emailTo = emailTo;
		this.actionURL = actionUrl;
		UUID = uUID;
		this.urlName = urlName;
		this.title = title;
		this.body = body;
		this.URL = "http://localhost:4200/" + this.actionURL + "/" + UUID;
	}
	@Override
	public void run() {
		//ENVIO DE EMAIL
		SendEmail.send("to",emailTo,
			    "title",this.title,
			    "body",String.format(
			    "<h1>%s</h1><a href='%s'>%s</a>",
			    this.body,
			    URL,
			    this.urlName)
			);	
	}
	
	

}
