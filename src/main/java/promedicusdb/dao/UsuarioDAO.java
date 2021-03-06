package promedicusdb.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import promedicusdb.consumes.PacienteFiltroConsume;
import promedicusdb.consumes.ResetPassConsume;
import promedicusdb.consumes.UsuarioFilterConsume;
import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Especialidad;
import promedicusdb.model.Medico;
import promedicusdb.model.ObraSocial;
import promedicusdb.model.Paciente;
import promedicusdb.model.Usuario;
import promedicusdb.util.LoginResult;

public class UsuarioDAO {
	
	public Boolean updateFromPaciente(Usuario usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Usuario unUsuario = this.getUsuario(usuario.getEmail());
		unUsuario.setNombre(usuario.getNombre());
		unUsuario.setApellido(usuario.getApellido());
		session.beginTransaction();
		session.update(unUsuario);
		session.getTransaction().commit();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsuarioWithFilter(UsuarioFilterConsume usuarioFilterConsume) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Usuario> usuarios = null;
		try {
			Criteria criteria = session.createCriteria(Usuario.class);
			if (usuarioFilterConsume.getApellido() != null) {
				criteria.add(Restrictions.like("apellido", usuarioFilterConsume.getApellido(), MatchMode.ANYWHERE));
			}
			if (usuarioFilterConsume.getNombre() != null) {
				criteria.add(Restrictions.like("nombre", usuarioFilterConsume.getNombre(), MatchMode.ANYWHERE));
			}
			if (usuarioFilterConsume.getIdPermiso() != null) {
				criteria.add(Restrictions.eq("nivelPermiso", usuarioFilterConsume.getIdPermiso()));
			}
			usuarios = criteria.list();
		} catch (Exception e) {

		} finally {
			session.getTransaction().commit();
		}
		
		return usuarios;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> getAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Usuario.class);
		List<Usuario> usuarios = null;
		try {
			usuarios = (List<Usuario>)criteria.list();
		} catch (Exception e) {
			
		} finally {
			session.getTransaction().commit();
		}
		
		return usuarios;
	}
	
	public Usuario getByEmailLike(String email) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();	
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add((Restrictions.like("Email", email, MatchMode.ANYWHERE)));
		Usuario usuario = (Usuario)criteria.uniqueResult();
		session.getTransaction().commit();
		return usuario;
	}
	
	public Boolean habilitar(String idUsuario) {
		Usuario usuario = this.getUsuario(idUsuario);
		usuario.setActivo(true);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(usuario);
		session.getTransaction().commit();
		return true;
	}
	
	public Boolean desabilitar(String idUsuario) {
		Usuario usuario = this.getUsuario(idUsuario);
		usuario.setActivo(false);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(usuario);
		session.getTransaction().commit();
		return true;
	}
	
	public Boolean existeEmail(String email) {
		Usuario usuario = this.getUsuario(email);
		return (usuario == null) ? false : true;
	}
	
	public Boolean resetPassword(ResetPassConsume resetPassConsume) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add((Restrictions.eq("pathReset", resetPassConsume.uuid)));
		Usuario usuario = (Usuario)criteria.uniqueResult();
		session.getTransaction().commit();
		
		if (usuario == null) {
			return false;
		}
		else {
			usuario.setPassword(resetPassConsume.password);
			this.updateUsuario(usuario);
			return true;
		}
	}
	
	public void updateUsuario(Usuario usuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();	
		session.update(usuario);
		session.getTransaction().commit();
	}
	
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
