package promedicusdb.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import promedicusdb.consumes.ErrorResponse;
import promedicusdb.consumes.TokenConsume;
import promedicusdb.dao.PacienteDAO;
import promedicusdb.model.Paciente;

@Path("paciente")
public class PacienteResource {
	
	@POST
	@Path("/get-all")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getPaciente(TokenConsume token) {
		PacienteDAO pacienteDAO = new PacienteDAO();
		Paciente unPaciente = pacienteDAO.getPaciente(token.getToken());
		
		if (unPaciente == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(2,"Error de usuario o password")
					).build();
		}
		
		return Response.ok(unPaciente, MediaType.APPLICATION_JSON).build();
	}

}
