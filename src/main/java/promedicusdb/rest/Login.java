package promedicusdb.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("login")
public class Login {
		
	@GET
	@Path("{nick}/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getVerificarUsuario(@PathParam("nick") String nick) {
		
		return "{'nick':'" + nick + "'}";
	}
}
