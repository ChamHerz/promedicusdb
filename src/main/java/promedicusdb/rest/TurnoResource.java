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

import promedicusdb.consumes.ErrorResponse;
import promedicusdb.consumes.TurnosConsultaConsume;
import promedicusdb.dao.TurnoDAO;
import promedicusdb.model.Turno;

@Path("turno")
public class TurnoResource {
	
	@POST
	@Path("/get-turnos")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getTurnos(TurnosConsultaConsume turnos) {
		TurnoDAO turnoDAO = new TurnoDAO();
		List<Turno> listaTurnos = turnoDAO.getTurnos(turnos);
		
		return Response.ok(listaTurnos, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/{idTurno}/")
	@Produces("application/json")
	public Response getTurno(@PathParam("idTurno") int idTurno) {
		TurnoDAO turnoDAO = new TurnoDAO();
		Turno turno = turnoDAO.getTurno(idTurno);
		
		if (turno == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity(
					new ErrorResponse(1,"medico no existente")).build();
		}
		
		return Response.ok(turno, MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Path("/add-turnos")
	@Consumes("application/json")
	@Produces("text/plain")
	public Response addTurnos(List<Turno> listaTurnos) {
		TurnoDAO turnoDAO = new TurnoDAO();
		Boolean resultado = turnoDAO.addTurnos(listaTurnos);
		
		return Response.ok(resultado.toString(), MediaType.TEXT_PLAIN).build();
	}
}
