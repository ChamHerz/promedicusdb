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
import promedicusdb.dao.SecretariaDAO;
import promedicusdb.model.Medico;
import promedicusdb.model.Secretaria;

@Path("secretaria")
public class SecretariaResource {
	
	@GET
	@Path("get-all-names")
	@Produces("application/json")
	public Response getAllSecretariasNames() {
		SecretariaDAO secretariaDAO = new SecretariaDAO();
		List<Secretaria> secretaria = secretariaDAO.getAllSecretariasNames();
		
		if (secretaria == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"medico no existente")).build();
		}
		
		return Response.ok(secretaria, MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("by-email/{email}/")
	@Produces("application/json")
	public Response getSecretariaByEmail(@PathParam("email") String email) {
		SecretariaDAO secretariaDAO = new SecretariaDAO();
		Secretaria secretaria = secretariaDAO.getSecretariaByEmail(email);
		
		if (secretaria == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"secretaria no existente")).build();
		}
		
		return Response.ok(secretaria, MediaType.APPLICATION_JSON).build();
	}
	
}
