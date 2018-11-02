package promedicusdb.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import promedicusdb.consumes.ErrorResponse;
import promedicusdb.consumes.ResetPassConsume;
import promedicusdb.consumes.TokenConsume;
import promedicusdb.dao.UsuarioDAO;
import promedicusdb.model.Usuario;
import promedicusdb.util.EmailThread;

@Path("usuario")
public class UsuarioResource {
	
	@GET
	@Path("/get-by-email-like/{email}/")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response getByNombreOrApellido(@PathParam("nombre") String email) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.getByEmailLike(email);
		
		if ( usuario == null)
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"Error al cargar usuario")
					).build();
		return Response.ok(usuario, MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Path("/habilitar/{email}/")
	@Consumes("text/plain")
	@Produces("text/plain")
	public Response habilitar(@PathParam("email") String email) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Boolean validado = usuarioDAO.habilitar(email);

		return Response.ok(validado.toString(), MediaType.TEXT_PLAIN).build();
	}
	
	@PUT
	@Path("/desabilitar/{email}/")
	@Consumes("text/plain")
	@Produces("text/plain")
	public Response desabilitar(@PathParam("email") String email) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Boolean validado = usuarioDAO.desabilitar(email);

		return Response.ok(validado.toString(), MediaType.TEXT_PLAIN).build();
	}
	
	@GET
	@Path("/existe-email/{email}/")
	@Produces("text/plain")
	public Response existeEmail(@PathParam("email") String email) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Boolean existe = usuarioDAO.existeEmail(email);
		
		if (existe) {
			Usuario usuario = usuarioDAO.getUsuario(email);
			EmailThread hiloEmail = new EmailThread(
					usuario.getEmail(),
					"reset-pass",
					usuario.getPathReset(),
					"Link para reset password",
					"Reset Password",
					"El siguiente link te permitir� resetear tu password"
					);
			hiloEmail.start();
		}
		
		return Response.ok(existe.toString(), MediaType.TEXT_PLAIN).build();
	}
	
	@PUT
	@Path("/reset-pass")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response resetPassword(ResetPassConsume resetPass) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Boolean resetResult = usuarioDAO.resetPassword(resetPass);
		return Response.ok(resetResult.toString(), MediaType.TEXT_PLAIN).build();		
	}		

	
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
		
		EmailThread hiloEmail = new EmailThread(
				usuario.getEmail(),
				"register-email",
				usuario.getPathReset(),
				"Link para activar cuenta",
				"Activar Email",
				"El siguiente link te permitir� activar tu cuenta"
				);
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
