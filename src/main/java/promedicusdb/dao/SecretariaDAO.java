package promedicusdb.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Secretaria;

public class SecretariaDAO {

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
