package promedicusdb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Medico;
import promedicusdb.model.Secretaria;

public class SecretariaDAO {
	
	@SuppressWarnings("unchecked")
	public List<Secretaria> getAllSecretariasNames() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Secretaria> medicos = null;
		try {
			Criteria criteria = session.createCriteria(Secretaria.class)
					.setProjection( Projections.projectionList()
							.add( Projections.property("nroLegajo"),"nroLegajo")
							.add( Projections.property("nombre"),"nombre")
							.add( Projections.property("apellido"),"apellido"))
					.addOrder(Order.asc("apellido"));
			criteria.setResultTransformer(new AliasToBeanResultTransformer(Secretaria.class));
			medicos = criteria.list();
		} catch (Exception e) {

		} finally {
			session.getTransaction().commit();
		}
		
		return medicos;
	}

	public Secretaria getSecretariaByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();	
		Criteria criteria = session.createCriteria(Secretaria.class);
		criteria.add((Restrictions.eq("email", email)));
		Secretaria secretaria = (Secretaria)criteria.uniqueResult();
		session.getTransaction().commit();
		return secretaria;
	}
	
}
