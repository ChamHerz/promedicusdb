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
		this.URL = "http://localhost:4200/#/" + this.actionURL + "/" + UUID;
	}
	@Override
	public void run() {
		//ENVIO DE EMAIL
//		if (this.actionURL == "reset-pass") {
//			SendEmail.send("to",emailTo,
//				    "title",this.title,
//				    "body",String.format(
//				    "<h1>%s</h1><a href='%s'>%s</a>",
//				    this.body,
//				    URL,
//				    this.urlName)
//				);
//			return;
//		}
		if (this.actionURL == "reset-pass") {
			SendEmail.send("to",emailTo,
				    "title",this.title,
				    "body",String.format(
				    "<div style='font-size: 50px;text-align: center;padding: 30px 0;border: 5px solid black;background-color: rgb(63,81,181);color: black;margin: auto;width: 400px'>Promedicus</div><div style='text-align: center;padding: 30px 0;color: black;margin: auto;'><h2>%s</h2></div><div style='text-align: center;padding: 30px 0;color: black;margin: auto;'><div style='border: solid 1px;margin: auto;background-color: rgb(63,81,181);padding: 5px;width: 120px;'><a style='color: white' href='%s'><b>%s</b></a></div></div>",
				    this.body,
				    URL,
				    this.urlName)
				);
			return;
		}
		if (this.actionURL == "send-turno") {
			SendEmail.send("to",emailTo,
				    "title",this.title,
				    "body",String.format(
				    "<div style='font-size: 50px;text-align: center;padding: 30px 0;border: 5px solid black;background-color: rgb(63,81,181);color: black;margin: auto;width: 400px'>Promedicus</div><div style='text-align: center;padding: 30px 0;color: black;margin: auto;'><h1>%s</h1></div><div style='text-align: center;padding: 30px 0;color: black;margin: auto;'><h2>%s</h2></div>",
				    this.body,
				    this.urlName)
				);
			return;
		}
	}
	
}
