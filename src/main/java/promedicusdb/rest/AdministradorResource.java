package promedicusdb.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import promedicusdb.consumes.ErrorResponse;
import promedicusdb.dao.AdministradorDAO;
import promedicusdb.model.Administrador;

@Path("admin")
public class AdministradorResource {

	@GET
	@Path("get-by-email/{email}/")
	@Produces("application/json")
	public Response getAdminByEmail(@PathParam("email") String email) {
		AdministradorDAO adminDAO = new AdministradorDAO();
		Administrador admin = adminDAO.getAdminByEmail(email);
		
		if (admin == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"admin no existente")).build();
		}
		
		return Response.ok(admin, MediaType.APPLICATION_JSON).build();
	}
}
