package promedicusdb.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Login;

@Path("login")
public class LoginResource {
		
	@GET
	@Path("{nick}/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getVerificarUsuario(@PathParam("nick") String nick) {
		// Se obtiene la sesion
        Session s = HibernateUtil.getSession();
        s.beginTransaction();

        // Se instancia la clase Flight y se rellenan sus datos
        Login f = new Login();
        f.setNombre("Nombre vuelo");
        
        // Se salva en base de datos
        s.save(f);
        s.getTransaction().commit();
		
		return "{'nick':'" + nick + "'}";
	}
}
