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


import promedicusdb.dao.MedicoSecretariaDAO;
import promedicusdb.model.MedicoSecretaria;

@Path("medico-secretaria")
public class MedicoSecretariaResource {
	
	@POST
	@Path("set-by-medico/{nrolegajo}/")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response setByMedico(
			@PathParam("nrolegajo") int nrolegajoMedico,
			List<MedicoSecretaria> medicoSecretaria) {
		MedicoSecretariaDAO medicoSecretariaDAO = new MedicoSecretariaDAO();
		Boolean resultado = medicoSecretariaDAO.setByMedico(nrolegajoMedico,medicoSecretaria);
		
		if (resultado == null) {
			return Response.ok("Not found", MediaType.TEXT_PLAIN).build();
		}
		
		return Response.ok(resultado, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("get-by-medico/{nrolegajo}/")
	@Produces("text/plain")
	public Response getByMedico(@PathParam("nrolegajo") int nrolegajoMedico) {
		MedicoSecretariaDAO medicoSecretariaDAO = new MedicoSecretariaDAO();
		List<MedicoSecretaria> medicoSecretaria = medicoSecretariaDAO.getByMedico(nrolegajoMedico);
		
		if (medicoSecretaria == null) {
			return Response.ok("Not found", MediaType.TEXT_PLAIN).build();
		}
		
		return Response.ok(medicoSecretaria, MediaType.APPLICATION_JSON).build();
	}

}
