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
import promedicusdb.consumes.MedicoFilterConsume;
import promedicusdb.consumes.PacienteFiltroConsume;
import promedicusdb.dao.MedicoDAO;
import promedicusdb.dao.PacienteDAO;
import promedicusdb.model.Medico;
import promedicusdb.model.Paciente;

@Path("medico")
public class MedicoResource {
	
	@PUT
	@Path("/update-from-admin")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response updateFromAdmin(Medico medico) {
		MedicoDAO medicoDAO = new MedicoDAO();
		Boolean resultado = medicoDAO.updateFromMedico(medico);
		
		return Response.ok(resultado.toString(), MediaType.TEXT_PLAIN).build();
	}
	
	@POST
	@Path("/new-medico")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response newMedico(Medico medico) {
		MedicoDAO medicoDAO = new MedicoDAO();
		Boolean resultadoInsert = medicoDAO.newMedico(medico);	
		return Response.ok(resultadoInsert, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/get-all-with-filter")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getMedicoWithFilter(MedicoFilterConsume medicoFiltroConsume) {
		MedicoDAO medicoDAO = new MedicoDAO();
		List<Medico> medicos = medicoDAO.getMedicoWithFilter(medicoFiltroConsume);
		
		if (medicos == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(2,"Error de usuario o password")
					).build();
		}
		
		return Response.ok(medicos, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/get-all-with-filter-only-secretary/{nrolegajo}/")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getMedicoWithFilterOnlySecretaria(
			@PathParam("nrolegajo") int nrolegajo,
			MedicoFilterConsume medicoFiltroConsume) {
		MedicoDAO medicoDAO = new MedicoDAO();
		List<Medico> medicos = medicoDAO.getMedicoWithFilterOnlySecretary(nrolegajo,medicoFiltroConsume);
		
		if (medicos == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(2,"Error de usuario o password")
					).build();
		}
		
		return Response.ok(medicos, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("get-all-names")
	@Produces("application/json")
	public Response getAllMedicoNames() {
		MedicoDAO medicoDAO = new MedicoDAO();
		List<Medico> medicos = medicoDAO.getAllMedicoNames();
		
		if (medicos == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"medico no existente")).build();
		}
		
		return Response.ok(medicos, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("by-email/{email}/")
	@Produces("application/json")
	public Response getMedicoByEmail(@PathParam("email") String email) {
		MedicoDAO medicoDAO = new MedicoDAO();
		Medico medico = medicoDAO.getMedicoByEmail(email);
		
		if (medico == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"medico no existente")).build();
		}
		
		return Response.ok(medico, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{nroLegajo}/")
	@Produces("application/json")
	public Response getMedico(@PathParam("nroLegajo") int nroLegajo) {
		MedicoDAO medicoDAO = new MedicoDAO();
		Medico medico = medicoDAO.getMedico(nroLegajo);
		
		if (medico == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"medico no existente")).build();
		}
		
		return Response.ok(medico, MediaType.APPLICATION_JSON).build();
	}

}
