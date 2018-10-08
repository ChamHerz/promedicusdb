package promedicusdb.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import promedicusdb.consumes.ErrorResponse;
import promedicusdb.dao.MedicoDAO;
import promedicusdb.model.Medico;

@Path("medico")
public class MedicoResource {
	
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
