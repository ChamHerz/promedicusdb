package promedicusdb.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Administrador;

public class AdministradorDAO {

	public Administrador getAdminByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();	
		Criteria criteria = session.createCriteria(Administrador.class);
		criteria.add((Restrictions.eq("email", email)));
		Administrador administrador = (Administrador)criteria.uniqueResult();
		session.getTransaction().commit();
		return administrador;
	}
	
}
