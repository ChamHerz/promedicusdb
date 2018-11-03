package promedicusdb.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Especialidad;
import promedicusdb.model.ObraSocial;
import promedicusdb.model.Turno;

public class ObraSocialDAO {
	
	public Boolean activar(int idObraSocial) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ObraSocial obraSocial = (ObraSocial)session.get(ObraSocial.class,idObraSocial);
		obraSocial.setActiva(true);
		session.update(obraSocial);
		session.getTransaction().commit();
		return true;
	}
	
	public Boolean desactivar(int idObraSocial) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ObraSocial obraSocial = (ObraSocial)session.get(ObraSocial.class,idObraSocial);
		obraSocial.setActiva(false);
		session.update(obraSocial);
		session.getTransaction().commit();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<ObraSocial> getAllObraSocialesByDenomicacion(String denominacion) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(ObraSocial.class);
		criteria.add(Restrictions.like("denominacion", denominacion, MatchMode.ANYWHERE));
		List<ObraSocial> obraSociales = null;
		try {
			obraSociales = (List<ObraSocial>)criteria.list();
		} catch (Exception e) {
			
		} finally {
			session.getTransaction().commit();
		}
		
		return obraSociales;
	}
	
	@SuppressWarnings("unchecked")
	public List<ObraSocial> getAllObraSociales() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(ObraSocial.class);
		List<ObraSocial> obraSociales = null;
		try {
			obraSociales = (List<ObraSocial>)criteria.list();
		} catch (Exception e) {
			
		} finally {
			session.getTransaction().commit();
		}
		
		return obraSociales;
	}
	
	public ObraSocial getObraSocial(int idObraSocial) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ObraSocial obraSocial = (ObraSocial)session.get(ObraSocial.class,idObraSocial);
		session.getTransaction().commit();
		return obraSocial;
	}
}
