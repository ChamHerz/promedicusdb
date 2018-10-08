package promedicusdb.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import promedicusdb.dao.EspecialidadDAO;
import promedicusdb.model.Especialidad;

@Path("especialidad")
public class EspecialidadResource {
	
	@GET
	@Path("get-all")
	@Produces("text/plain")
	public Response getAllEspecialidad() {
		EspecialidadDAO especialidadDAO = new EspecialidadDAO();
		List<Especialidad> especialidades = especialidadDAO.getAllEspecialidad();
		
		if (especialidades == null) {
			return Response.ok("Not found", MediaType.TEXT_PLAIN).build();
		}
		
		return Response.ok(especialidades, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{idEspecialidad}/")
	@Produces("text/plain")
	public Response getEspecialidad(@PathParam("idEspecialidad") int idEspecialidad) {
		EspecialidadDAO especialidadDAO = new EspecialidadDAO();
		Especialidad especialidad = especialidadDAO.getEspecialidad(idEspecialidad);
		
		if (especialidad == null) {
			return Response.ok("Not found", MediaType.TEXT_PLAIN).build();
		}
		
		return Response.ok(especialidad.getDescripcion(), MediaType.TEXT_PLAIN).build();
	}

}
