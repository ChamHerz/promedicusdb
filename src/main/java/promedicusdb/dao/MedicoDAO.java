package promedicusdb.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;

import promedicusdb.consumes.MedicoFilterConsume;
import promedicusdb.consumes.PacienteFiltroConsume;
import promedicusdb.main.HibernateUtil;
import promedicusdb.model.Especialidad;
import promedicusdb.model.Medico;
import promedicusdb.model.MedicoSecretaria;
import promedicusdb.model.Paciente;
import promedicusdb.model.Usuario;

public class MedicoDAO {
	
	public Boolean updateFromMedico(Medico medico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Medico unMedico = this.getMedico(medico.getNroLegajo());
		unMedico.setNombre(medico.getNombre());
		unMedico.setApellido(medico.getApellido());
		unMedico.setDni(medico.getDni());
		unMedico.setDireccion(medico.getDireccion());
		unMedico.setTelefono(medico.getTelefono());
		unMedico.setEspecialidad(medico.getEspecialidad());
		session.beginTransaction();
		session.update(unMedico);
		session.getTransaction().commit();
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.getUsuario(medico.getEmail());
		usuario.setNombre(unMedico.getNombre());
		usuario.setApellido(unMedico.getApellido());
		session.beginTransaction();
		session.update(usuario);
		session.getTransaction().commit();
		
		return true;
	}
	
	public Boolean newMedico(Medico medico) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(medico);
		session.getTransaction().commit();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<Medico> getMedicoWithFilter(MedicoFilterConsume medicoFiltroConsume) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		List<Medico> listaMedicos = null;
		try {
			Criteria criteria = session.createCriteria(Medico.class);
			if (medicoFiltroConsume.getApellido() != null) {
				criteria.add(Restrictions.like("apellido", medicoFiltroConsume.getApellido(), MatchMode.ANYWHERE));
			}
			if (medicoFiltroConsume.getNombre() != null) {
				criteria.add(Restrictions.like("nombre", medicoFiltroConsume.getNombre(), MatchMode.ANYWHERE));
			}
			if (medicoFiltroConsume.getIdEspecialidad() != null) {
				EspecialidadDAO especialidadDAO = new EspecialidadDAO();
				Especialidad especialidad = especialidadDAO.getEspecialidad(medicoFiltroConsume.getIdEspecialidad());
				criteria.add(Restrictions.eq("especialidad", especialidad));
			}
			listaMedicos = criteria.list();
		} catch (Exception e) {

		} finally {
			session.getTransaction().commit();
		}
		
		return listaMedicos;
	}
	
	
	public List<Medico> getMedicoWithFilterOnlySecretary(int nrolegajo, MedicoFilterConsume medicoFiltroConsume) {
		List<Medico> listaMedicoSoloFilter = getMedicoWithFilter(medicoFiltroConsume);
		
		MedicoSecretariaDAO medicoSecretariaDAO = new MedicoSecretariaDAO();
		List<MedicoSecretaria> misMedicosSecretaria = medicoSecretariaDAO.getBySecretaria(nrolegajo);
		
		List<Medico> medicosDevolver = new ArrayList<Medico>();
		
		misMedicosSecretaria.forEach(medicoSecretaria -> {
			listaMedicoSoloFilter.forEach(medico -> {
				if (medico.getNroLegajo() == medicoSecretaria.getNroLegajoMedico()) {
					medicosDevolver.add(medico);
				}
			});
		});		
		
		return medicosDevolver;
	}
	
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
