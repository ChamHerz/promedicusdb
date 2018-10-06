package promedicusdb.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import promedicusdb.consumes.ErrorResponse;
import promedicusdb.consumes.TokenConsume;
import promedicusdb.dao.UsuarioDAO;
import promedicusdb.model.Usuario;
import promedicusdb.util.EmailThread;

@Path("usuario")
public class UsuarioResource {
	
	@PUT
	@Path("/validar-email")
	@Consumes("text/plain")
	@Produces("text/plain")
	public Response validarEmail(String uuid) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Boolean validado = usuarioDAO.validarEmail(uuid);

		return Response.ok(validado.toString(), MediaType.TEXT_PLAIN).build();
	}
	
	@POST
	@Path("/new")
	@Consumes("application/json")
	@Produces("application/json")
	public Response nuevoUsuario(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.newUser(usuario);
		
		EmailThread hiloEmail = new EmailThread(usuario.getEmail(),usuario.getPathReset());
		hiloEmail.start();

		return Response.ok(usuario, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/get-one")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getUsuario(TokenConsume token) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.getUsuario(token.getToken());
		
		if ( usuario == null)
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"Error al cargar usuario")
					).build();
		return Response.ok(usuario, MediaType.APPLICATION_JSON).build();
	}
}
