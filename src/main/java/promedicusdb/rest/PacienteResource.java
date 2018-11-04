package promedicusdb.rest;

import java.util.List;

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
import promedicusdb.consumes.PacienteFiltroConsume;
import promedicusdb.consumes.TokenConsume;
import promedicusdb.dao.PacienteDAO;
import promedicusdb.dao.UsuarioDAO;
import promedicusdb.model.Paciente;
import promedicusdb.model.Usuario;

@Path("paciente")
public class PacienteResource {
	
	@PUT
	@Path("/update-from-paciente")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response updateFromPaciente(Paciente paciente) {
		PacienteDAO pacienteDAO = new PacienteDAO();
		Boolean resultado = pacienteDAO.updateFromPaciente(paciente);
		
		return Response.ok(resultado.toString(), MediaType.TEXT_PLAIN).build();
	}
	
	@POST
	@Path("/get-all-with-filter")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getPacienteWithFilter(PacienteFiltroConsume pacienteFiltroConsume) {
		PacienteDAO pacienteDAO = new PacienteDAO();
		List<Paciente> pacientes = pacienteDAO.getPacienteWithFilter(pacienteFiltroConsume);
		
		if (pacientes == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(2,"Error de usuario o password")
					).build();
		}
		
		return Response.ok(pacientes, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("get-all")
	@Produces("application/json")
	public Response getAll() {
		PacienteDAO pacienteDAO = new PacienteDAO();
		List<Paciente> pacientes = pacienteDAO.getAll();
		
		if (pacientes == null) {
			return Response.ok("Not found", MediaType.TEXT_PLAIN).build();
		}
		
		return Response.ok(pacientes, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/get-by-dni/{dni}/")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response getPacienteByDni(@PathParam("dni") String dni) {
		PacienteDAO pacienteDAO = new PacienteDAO();
		Paciente paciente = pacienteDAO.getPacienteByDni(dni);
		
		if (paciente == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"No existe paciente")).build();
		}
		
		return Response.ok(paciente, MediaType.APPLICATION_JSON).build();
	}
	
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
