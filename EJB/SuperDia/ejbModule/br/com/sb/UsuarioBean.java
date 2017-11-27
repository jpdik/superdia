package br.com.sb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
		try {
			em.persist(usuario);
			return true;
		}catch (EntityExistsException e) {
			return false;
		}
	}

	@Override
	public boolean altera(Usuario usuario) {
		try {
			em.merge(usuario);
			return true;
		}catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean remove(Usuario usuario) {
		try {
			em.remove(em.merge(usuario));
			return true;
		}catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public List<Usuario> listaTodos() {
		CriteriaQuery<Usuario> cq = em.getCriteriaBuilder().createQuery(Usuario.class);
		
		cq.select(cq.from(Usuario.class));
		
		return em.createQuery(cq).getResultList();
	}

}
