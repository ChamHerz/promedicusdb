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
import promedicusdb.model.Usuario;

public class SecretariaDAO {
	
	public Boolean updateFromAdmin(Secretaria secretaria) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Secretaria unaSecretaria = this.getSecretaria(secretaria.getNroLegajo());
		unaSecretaria.setNombre(secretaria.getNombre());
		unaSecretaria.setApellido(secretaria.getApellido());
		unaSecretaria.setDni(secretaria.getDni());
		unaSecretaria.setDireccion(secretaria.getDireccion());
		unaSecretaria.setTelefono(secretaria.getTelefono());
		session.beginTransaction();
		session.update(unaSecretaria);
		session.getTransaction().commit();
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.getUsuario(secretaria.getEmail());
		usuario.setNombre(unaSecretaria.getNombre());
		usuario.setApellido(unaSecretaria.getApellido());
		session.beginTransaction();
		session.update(usuario);
		session.getTransaction().commit();
		
		return true;
	}
	
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
	
	public Secretaria getSecretaria(int nroLegajo) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Secretaria secretaria = (Secretaria)session.get(Secretaria.class,nroLegajo);
		session.getTransaction().commit();
		return secretaria;
	}
}
