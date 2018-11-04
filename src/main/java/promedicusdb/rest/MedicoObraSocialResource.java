package promedicusdb.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import promedicusdb.dao.MedicoObraSocialDAO;
import promedicusdb.dao.MedicoSecretariaDAO;
import promedicusdb.dao.ObraSocialDAO;
import promedicusdb.model.MedicoObraSocial;
import promedicusdb.model.MedicoSecretaria;

@Path("medico-obra-social")
public class MedicoObraSocialResource {
	
	@POST
	@Path("set-by-medico/{nrolegajo}/")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response setByMedico(
			@PathParam("nrolegajo") int nrolegajoMedico,
			List<MedicoObraSocial> medicoObraSocial) {
		MedicoObraSocialDAO medicoObraSocialDAO = new MedicoObraSocialDAO();
		Boolean resultado = medicoObraSocialDAO.setByMedico(nrolegajoMedico,medicoObraSocial);
		
		if (resultado == null) {
			return Response.ok("Not found", MediaType.TEXT_PLAIN).build();
		}
		
		return Response.ok(resultado, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("get/{nrolegajo}/")
	@Produces("text/plain")
	public Response get(@PathParam("nrolegajo") int nrolegajo) {
		MedicoObraSocialDAO medicoObraSocialDAO = new MedicoObraSocialDAO();
		List<MedicoObraSocial> medicoObraSociales = medicoObraSocialDAO.get(nrolegajo);
		
		if (medicoObraSociales == null) {
			return Response.ok("Not found", MediaType.TEXT_PLAIN).build();
		}
		
		return Response.ok(medicoObraSociales, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("new-medico-obra-social")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response newMedicoObraSocial(List<MedicoObraSocial> medicoObraSocial) {
		MedicoObraSocialDAO medicoObraSocialDAO = new MedicoObraSocialDAO();
		Boolean resultado = medicoObraSocialDAO.newMedicoObraSocial(medicoObraSocial);
		
		return Response.ok(resultado.toString(), MediaType.TEXT_PLAIN).build();
		
	}
	
	@GET
	@Path("get-all")
	@Produces("text/plain")
	public Response getAllEspecialidad() {
		MedicoObraSocialDAO medicoObraSocialDAO = new MedicoObraSocialDAO();
		List<MedicoObraSocial> medicoObraSociales = medicoObraSocialDAO.getAll();
		
		if (medicoObraSociales == null) {
			return Response.ok("Not found", MediaType.TEXT_PLAIN).build();
		}
		
		return Response.ok(medicoObraSociales, MediaType.APPLICATION_JSON).build();
	}

}
