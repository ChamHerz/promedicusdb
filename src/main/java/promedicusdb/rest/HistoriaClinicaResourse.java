package promedicusdb.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import promedicusdb.consumes.ErrorResponse;
import promedicusdb.dao.HistoriaClinicaDAO;
import promedicusdb.model.HistoriaClinica;

@Path("historia-clinica")
public class HistoriaClinicaResourse {
	
	@GET
	@Path("/get/{dni}/")
	@Consumes("text/plain")
	@Produces("application/json")
	public Response get(@PathParam("dni") String dni) {
		HistoriaClinicaDAO historiaClinicaDAO = new HistoriaClinicaDAO();
		HistoriaClinica historiaClinica = historiaClinicaDAO.get(dni);
		
		if (historiaClinica == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"No existe paciente")).build();
		}
		
		return Response.ok(historiaClinica, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("get-all")
	@Produces("text/plain")
	public Response getAllHistoriasClinicas() {
		HistoriaClinicaDAO historiaClinicaDAO = new HistoriaClinicaDAO();
		List<HistoriaClinica> historiaClinica = historiaClinicaDAO.getAllHistoriasClinicas();
		
		if (historiaClinica == null) {
			return Response.ok("Not found", MediaType.TEXT_PLAIN).build();
		}
		
		return Response.ok(historiaClinica, MediaType.APPLICATION_JSON).build();
	}

}
