package promedicusdb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Especialidad;
import promedicusdb.model.Medico;

public class MedicoDAO {
	
	@SuppressWarnings("unchecked")
	public List<Medico> getAllMedicoNames() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Medico> medicos = null;
		try {
			Criteria criteria = session.createCriteria(Medico.class)
					.setProjection( Projections.projectionList()
							.add( Projections.property("nroLegajo"),"nroLegajo")
							.add( Projections.property("nombre"),"nombre")
							.add( Projections.property("apellido"),"apellido")
							);
			criteria.setResultTransformer(new AliasToBeanResultTransformer(Medico.class));
			medicos = criteria.list();
		} catch (Exception e) {

		} finally {
			session.getTransaction().commit();
		}
		
		return medicos;
	}
	
	public Medico getMedicoByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();	
		Criteria criteria = session.createCriteria(Medico.class);
		criteria.add((Restrictions.eq("email", email)));
		Medico medico = (Medico)criteria.uniqueResult();
		session.getTransaction().commit();
		return medico;
	}
	
	public Medico getMedico(int nroLegajo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Medico medico = (Medico)session.get(Medico.class,nroLegajo);
		session.getTransaction().commit();
		return medico;
	}

}
