package promedicusdb.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import promedicusdb.consumes.ErrorResponse;
import promedicusdb.dao.ObraSocialDAO;
import promedicusdb.model.ObraSocial;

@Path("obra-social")
public class ObraSocialResource {
	
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
