package promedicusdb.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import promedicusdb.consumes.ErrorResponse;
import promedicusdb.consumes.TokenConsume;
import promedicusdb.dao.PacienteDAO;
import promedicusdb.model.Paciente;

@Path("paciente")
public class PacienteResource {
	
	@GET
	@Path("/get-by-email/{email}/")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response getPacienteByEmail(@PathParam("email") String email) {
		PacienteDAO pacienteDAO = new PacienteDAO();
		Paciente paciente = pacienteDAO.getPaciente(email);
		
		if (paciente == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"No existe paciente")).build();
		}
		
		return Response.ok(paciente, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/new")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response newPaciente(Paciente paciente) {
		PacienteDAO pacienteDAO = new PacienteDAO();
		Boolean resultadoInsert = pacienteDAO.newPaciente(paciente);	
		return Response.ok(resultadoInsert, MediaType.APPLICATION_JSON).build();
	}
	
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
