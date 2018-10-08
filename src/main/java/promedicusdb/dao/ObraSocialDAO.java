package promedicusdb.dao;

import org.hibernate.Session;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.ObraSocial;

public class ObraSocialDAO {
	
	public ObraSocial getObraSocial(int idObraSocial) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ObraSocial obraSocial = (ObraSocial)session.get(ObraSocial.class,idObraSocial);
		session.getTransaction().commit();
		return obraSocial;
	}
}
