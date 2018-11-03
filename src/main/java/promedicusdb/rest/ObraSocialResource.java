package promedicusdb.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import promedicusdb.consumes.ErrorResponse;
import promedicusdb.dao.EspecialidadDAO;
import promedicusdb.dao.ObraSocialDAO;
import promedicusdb.dao.TurnoDAO;
import promedicusdb.model.Especialidad;
import promedicusdb.model.ObraSocial;

@Path("obra-social")
public class ObraSocialResource {
	
	@PUT
	@Path("/activar/{idObraSocial}/")
	@Produces("text/plain")
	public Response activar(@PathParam("idObraSocial") int idObraSocial) {
		ObraSocialDAO obraSocialDAO = new ObraSocialDAO();
		Boolean resultado = obraSocialDAO.activar(idObraSocial);
		
		return Response.ok(resultado.toString(), MediaType.TEXT_PLAIN).build();
	}
	
	@PUT
	@Path("/desactivar/{idObraSocial}/")
	@Produces("text/plain")
	public Response desactivar(@PathParam("idObraSocial") int idObraSocial) {
		ObraSocialDAO obraSocialDAO = new ObraSocialDAO();
		Boolean resultado = obraSocialDAO.desactivar(idObraSocial);
		
		return Response.ok(resultado.toString(), MediaType.TEXT_PLAIN).build();
	}
	
	@GET
	@Path("get-all-by-denominacion/{deniminacion}/")
	@Produces("text/plain")
	public Response getAllObraSocialesByDenomicacion(@PathParam("deniminacion") String deniminacion) {
		ObraSocialDAO obraSocialDAO = new ObraSocialDAO();
		List<ObraSocial> obraSociales = obraSocialDAO.getAllObraSocialesByDenomicacion(deniminacion);
		
		if (obraSociales == null) {
			return Response.ok("Not found", MediaType.TEXT_PLAIN).build();
		}
		
		return Response.ok(obraSociales, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("get-all")
	@Produces("text/plain")
	public Response getAllObraSociales() {
		ObraSocialDAO obraSocialDAO = new ObraSocialDAO();
		List<ObraSocial> obraSociales = obraSocialDAO.getAllObraSociales();
		
		if (obraSociales == null) {
			return Response.ok("Not found", MediaType.TEXT_PLAIN).build();
		}
		
		return Response.ok(obraSociales, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("by-id/{idObraSocial}/")
	@Produces("application/json")
	public Response getObraSocial(@PathParam("idObraSocial") int idObraSocual) {
		ObraSocialDAO obraSocialDAO = new ObraSocialDAO();
		ObraSocial obraSocial = obraSocialDAO.getObraSocial(idObraSocual);
		
		if (obraSocial == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"Obra Social no existente")).build();
		}
		
		return Response.ok(obraSocial, MediaType.APPLICATION_JSON).build();
	}
}
