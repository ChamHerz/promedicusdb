package promedicusdb.dao;

import org.hibernate.Session;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Configuracion;

public class ConfiguracionDAO {
	
	public void incrementar() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Configuracion configuracion = (Configuracion)session.get(Configuracion.class,"nroLegajo");
		int valor = configuracion.getValor();
		valor = valor + 1;
		configuracion.setValor(valor);
		session.update(configuracion);
		session.getTransaction().commit();
		return;
	}

	public Configuracion getConfiguracion(String idVariable) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Configuracion configuracion = (Configuracion)session.get(Configuracion.class,idVariable);
		session.getTransaction().commit();
		return configuracion;
	}
}
