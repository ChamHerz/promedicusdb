package promedicusdb.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Paciente;

public class PacienteDAO {
	
	public Boolean newPaciente(Paciente paciente) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(paciente);
		session.getTransaction().commit();
		return true;
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
