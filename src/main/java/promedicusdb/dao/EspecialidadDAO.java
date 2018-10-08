package promedicusdb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Especialidad;

public class EspecialidadDAO {
	
	@SuppressWarnings("unchecked")
	public List<Especialidad> getAllEspecialidad() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Especialidad.class);
		List<Especialidad> especialidades = null;
		try {
			especialidades = (List<Especialidad>)criteria.list();
		} catch (Exception e) {
			
		} finally {
			session.getTransaction().commit();
		}
		
		return especialidades;
	}
	
	public Especialidad getEspecialidad(int idEspecialidad) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Especialidad especialidad = (Especialidad)session.get(Especialidad.class,idEspecialidad);
		session.getTransaction().commit();
		return especialidad;
	}

}
