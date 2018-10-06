package promedicusdb.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import promedicusdb.consumes.ErrorResponse;
import promedicusdb.consumes.LoginConsume;
import promedicusdb.dao.LoginDAO;
import promedicusdb.dao.UsuarioDAO;
import promedicusdb.model.Login;
import promedicusdb.model.Usuario;
import promedicusdb.util.LoginResult;

@Path("login")
public class LoginResource {
		
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response getJSON(LoginConsume loginConsume) {
		LoginDAO loginDAO = new LoginDAO();
		//Login login = loginDAO.getLogin(loginConsume);
		
		LoginResult loginResult = loginDAO.getLogin(loginConsume);
		
		if ( loginResult == LoginResult.CORRECTO) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = usuarioDAO.getUsuario(loginConsume.getUsername());
			Login login = new Login();
			login.setToken(usuario.getEmail());
			login.setNivelPermiso(usuario.getNivelPermiso());
			return Response.ok(login, MediaType.APPLICATION_JSON).build();
		}
		
		if ( loginResult == LoginResult.EMAIL_ERROR)
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"Usuario no existente")).build();
		
		if ( loginResult == LoginResult.PASSWORD_ERROR)
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(2,"Contraseña incorrecta")).build();
		
		if ( loginResult == LoginResult.EMAIL_UNCHECK)
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(2,"Tiene que validar su email")).build();
		
		if ( loginResult == LoginResult.USER_BLOCK)
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(2,"Su cuenta está bloqueada")).build();
		
		return null;
	}
}
