package br.com.sb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.interfacebean.IUsuario;
import br.com.modelo.Usuario;

@Stateless
@Remote(IUsuario.class)
public class UsuarioBean implements IUsuario {
	@PersistenceContext(name="superdia")
	EntityManager em;
	
	@Override
	public boolean adiciona(Usuario usuario) {
		if(!entityExists(usuario)) {
			em.persist(usuario);
			return true;
		}
		return false;
	}

	@Override
	public boolean altera(Usuario usuario) {
		if(entityExists(usuario)) {
			em.merge(usuario);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Usuario usuario) {
		if(entityExists(usuario)) {
			em.remove(em.merge(usuario));
			return true;
		}
		return false;
	}

	@Override
	public List<Usuario> listaTodos() {
		CriteriaQuery<Usuario> cq = em.getCriteriaBuilder().createQuery(Usuario.class);
		
		cq.select(cq.from(Usuario.class));
		
		return em.createQuery(cq).getResultList();
	}
	
	private boolean entityExists(Usuario usuario) {
		try {
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario");
			
			q.setParameter("usuario", usuario.getUsuario());
			
			q.getSingleResult();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<String> getPerfis() {
		List<String> perfis = new ArrayList<>();
		for(Usuario.perfis perfil : Usuario.perfis.values())
			perfis.add(perfil.name());
		return perfis;
	}

}
