package promedicusdb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.MedicoObraSocial;

public class MedicoObraSocialDAO {
	
	public Boolean setByMedico(
			int nrolegajoMedico,
			List<MedicoObraSocial> medicoObraSociales) {
		
		deleteByMedico(nrolegajoMedico);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (MedicoObraSocial medicoObraSocial : medicoObraSociales) {
			session.save(medicoObraSocial);
		}
		session.getTransaction().commit();

		return true;
	}
	
	private void deleteByMedico(int nroLegajoMedico) {
		List<MedicoObraSocial> listaMedicoObraSocial = get(nroLegajoMedico);
				
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (MedicoObraSocial medicoObraSocial : listaMedicoObraSocial) {
			session.delete(medicoObraSocial);
		}
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<MedicoObraSocial> get(int nrolegajo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();	
		Criteria criteria = session.createCriteria(MedicoObraSocial.class);
		criteria.add((Restrictions.eq("nroLegajo", nrolegajo)));	
		List<MedicoObraSocial> medicoObraSocial = criteria.list();
		session.getTransaction().commit();
		return medicoObraSocial;
	}
	
	public Boolean newMedicoObraSocial(List<MedicoObraSocial> medicoObraSociales) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (MedicoObraSocial medicoObraSocial : medicoObraSociales) {
			session.save(medicoObraSocial);
		}
		session.getTransaction().commit();
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<MedicoObraSocial> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(MedicoObraSocial.class);
		List<MedicoObraSocial> medicoObraSociales = null;
		try {
			medicoObraSociales = (List<MedicoObraSocial>)criteria.list();
		} catch (Exception e) {
			
		} finally {
			session.getTransaction().commit();
		}
		
		return medicoObraSociales;
	}

}
