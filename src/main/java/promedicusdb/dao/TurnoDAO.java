package promedicusdb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import promedicusdb.consumes.TurnosConsultaConsume;
import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Turno;
import promedicusdb.model.Usuario;

public class TurnoDAO {
	
	public Boolean confirmar(int idTurno) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Turno turno = (Turno)session.get(Turno.class,idTurno);
		turno.setEstadoTurno(4);
		session.update(turno);
		session.getTransaction().commit();
		return true;
	}
	
	public Boolean cancelar(int idTurno, int idPaciente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Turno turno = (Turno)session.get(Turno.class,idTurno);
		turno.setEstadoTurno(0);
		session.update(turno);
		session.getTransaction().commit();
		return true;
	}
	
	public Boolean solicitar(int idTurno, int idPaciente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Turno turno = (Turno)session.get(Turno.class,idTurno);
		turno.setIdPaciente(idPaciente);
		turno.setEstadoTurno(1);
		session.update(turno);
		session.getTransaction().commit();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Turno> getTurnosDePaciente(int idPaciente, int unEstado){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Turno> listaTurnos = null;
		try {
			Criteria criteria = session.createCriteria(Turno.class)
				.add((Restrictions.eq("idPaciente", idPaciente)));
			if (unEstado != -1) {
				criteria.add((Restrictions.eq("estadoTurno", unEstado)));
			}
			
			listaTurnos = criteria.list();
		} catch (Exception e) {

		} finally {
			session.getTransaction().commit();
		}
		
		return listaTurnos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Turno> getTurnosDeMedico(int nrolegajo, int unEstado){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Turno> listaTurnos = null;
		try {
			Criteria criteria = session.createCriteria(Turno.class)
				.add((Restrictions.eq("nroLegajo", nrolegajo)));
			if (unEstado != -1) {
				criteria.add((Restrictions.eq("estadoTurno", unEstado)));
			}
			
			listaTurnos = criteria.list();
		} catch (Exception e) {

		} finally {
			session.getTransaction().commit();
		}
		
		return listaTurnos;
	}
	
	@SuppressWarnings("unchecked")
	public List<Turno> getTurnos(TurnosConsultaConsume turnos){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Turno> listaTurnos = null;
		try {
			Criteria criteria = session.createCriteria(Turno.class)
				.add((Restrictions.eq("idEspecialidad", turnos.getEspecialidad())))
				.add((Restrictions.eq("estadoTurno", 5)))
				.add((Restrictions.between("fechaHora", turnos.getFechaDesde(), turnos.getFechaHasta())));
			if (turnos.getMedico() != 0) //viene con medico
				criteria.add((Restrictions.eq("nroLegajo", turnos.getMedico())));
			listaTurnos = criteria.list();
		} catch (Exception e) {

		} finally {
			session.getTransaction().commit();
		}
		
		return listaTurnos;
	}
	
	public Boolean addTurnos(List<Turno> listaTurnos) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (Turno turno : listaTurnos) {
			session.save(turno);
		}
		session.getTransaction().commit();
		
		return true;
	}
	
	public Turno getTurno(int idTurno) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Turno turno = (Turno)session.get(Turno.class,idTurno);
		session.getTransaction().commit();
		return turno;
	}

}
