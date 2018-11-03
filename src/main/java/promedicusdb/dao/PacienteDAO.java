package promedicusdb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import promedicusdb.consumes.PacienteFiltroConsume;
import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Paciente;
import promedicusdb.model.Turno;
import promedicusdb.model.Usuario;

public class PacienteDAO {
	
	@SuppressWarnings("unchecked")
	public List<Paciente> getPacienteWithFilter(PacienteFiltroConsume pacienteFiltroConsume) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Paciente> listaPacientes = null;
		try {
			Criteria criteria = session.createCriteria(Paciente.class);
			if (pacienteFiltroConsume.getDni() != null) {
				criteria.add(Restrictions.like("dni", pacienteFiltroConsume.getDni(),MatchMode.ANYWHERE));
			}
			if (pacienteFiltroConsume.getApellido() != null) {
				criteria.add(Restrictions.like("apellido", pacienteFiltroConsume.getApellido(), MatchMode.ANYWHERE));
			}
			if (pacienteFiltroConsume.getNombre() != null) {
				criteria.add(Restrictions.like("nombre", pacienteFiltroConsume.getNombre(), MatchMode.ANYWHERE));
			}
			listaPacientes = criteria.list();
		} catch (Exception e) {

		} finally {
			session.getTransaction().commit();
		}
		
		return listaPacientes;
	}
	
	@SuppressWarnings("unchecked")
	public List<Paciente> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Paciente> listaPacientes = null;
		try {
			Criteria criteria = session.createCriteria(Paciente.class);		
			listaPacientes = criteria.list();
		} catch (Exception e) {

		} finally {
			session.getTransaction().commit();
		}
		
		return listaPacientes;
	}
	
	public Boolean newPaciente(Paciente paciente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(paciente);
		session.getTransaction().commit();
		return true;
	}
	
	public Paciente getPacienteByDni(String dni) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Paciente paciente = (Paciente)session.get(Paciente.class,dni);
		session.getTransaction().commit();
		return paciente;
	}
	
	public Paciente getPaciente(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Paciente.class);
		criteria.add((Restrictions.eq("email", email)));
		
		Paciente unPaciente = (Paciente)criteria.uniqueResult();
		
		session.getTransaction().commit();
		
		return unPaciente;
	}

}
