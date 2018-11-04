package promedicusdb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.MedicoObraSocial;
import promedicusdb.model.MedicoSecretaria;

public class MedicoSecretariaDAO {
	
	public Boolean setByMedico(
			int nrolegajoMedico,
			List<MedicoSecretaria> medicoSecretarias) {
		
		deleteByMedico(nrolegajoMedico);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (MedicoSecretaria medicoSecretaria : medicoSecretarias) {
			session.save(medicoSecretaria);
		}
		session.getTransaction().commit();

		return true;
	}
	
	private void deleteByMedico(int nroLegajoMedico) {
		List<MedicoSecretaria> listaMedicoSecretaria = getByMedico(nroLegajoMedico);
				
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		for (MedicoSecretaria medicoSecretaria : listaMedicoSecretaria) {
			session.delete(medicoSecretaria);
		}
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<MedicoSecretaria> getBySecretaria(int nroLegajoSecretaria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();	
		Criteria criteria = session.createCriteria(MedicoSecretaria.class);
		criteria.add((Restrictions.eq("nroLegajoSecretaria", nroLegajoSecretaria)));	
		List<MedicoSecretaria> medicoSecretaria = criteria.list();
		session.getTransaction().commit();
		return medicoSecretaria;
	}
	
	@SuppressWarnings("unchecked")
	public List<MedicoSecretaria> getByMedico(int nroLegajoMedico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();	
		Criteria criteria = session.createCriteria(MedicoSecretaria.class);
		criteria.add((Restrictions.eq("nroLegajoMedico", nroLegajoMedico)));	
		List<MedicoSecretaria> medicoSecretaria = criteria.list();
		session.getTransaction().commit();
		return medicoSecretaria;
	}

}
