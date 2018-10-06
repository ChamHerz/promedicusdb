package promedicusdb.dao;

import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Usuario;
import promedicusdb.util.LoginResult;

public class UsuarioDAO {
	
	public Boolean validarEmail(String uuid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add((Restrictions.eq("pathReset", uuid)));
		
		Usuario usuario = (Usuario)criteria.uniqueResult();
		
		session.getTransaction().commit();
		
		if (usuario != null) {
			session.beginTransaction();	
			usuario.setEmailConfirm(true);		
			session.update(usuario);		
			session.getTransaction().commit();
		}
		
		return (usuario == null) ? false: true;
	}

	public Usuario getUsuario(String email){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Usuario unUsuario = (Usuario) session.get(Usuario.class,email);
		session.getTransaction().commit();
		return unUsuario;
	}
	
	public LoginResult getUsuarioByPassword(String email,String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Usuario unUsuario = (Usuario) session.get(Usuario.class,email);
		session.getTransaction().commit();
		if (unUsuario == null) {
			return LoginResult.EMAIL_ERROR;
		}
		if (unUsuario.getActivo() == false) {
			return LoginResult.USER_BLOCK;
		}
		if (unUsuario.getEmailConfirm() == false) {
			return LoginResult.EMAIL_UNCHECK;
		}
		if (unUsuario.getPassword().equals(password)) {
			return LoginResult.CORRECTO;
		}
		return LoginResult.PASSWORD_ERROR;
	}
	
	public Boolean newUser(Usuario usuario) {
		Boolean resultado;
		usuario.setPathReset(UUID.randomUUID().toString());
		usuario.setEmailConfirm(false);
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(usuario);
			session.getTransaction().commit();
			resultado = true;
		} catch(Exception e) {
			resultado = false;
		}
		
		return resultado;
	}
}
