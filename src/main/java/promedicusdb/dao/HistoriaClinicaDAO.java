package promedicusdb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.HistoriaClinica;
import promedicusdb.model.Paciente;

public class HistoriaClinicaDAO {
	
	public HistoriaClinica get(String dni) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();		
		Criteria criteria = session.createCriteria(HistoriaClinica.class);
		criteria.add((Restrictions.eq("dni", dni)));
		
		HistoriaClinica historiaClinica = (HistoriaClinica)criteria.uniqueResult();
		
		session.getTransaction().commit();
		
		return historiaClinica;
	}
	
	@SuppressWarnings("unchecked")
	public List<HistoriaClinica> getAllHistoriasClinicas() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(HistoriaClinica.class);
		List<HistoriaClinica> historiaClinica = null;
		try {
			historiaClinica = (List<HistoriaClinica>)criteria.list();
		} catch (Exception e) {
			
		} finally {
			session.getTransaction().commit();
		}
		
		return historiaClinica;
	}
}
