package br.com.sb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
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
	public void adiciona(Usuario usuario) {
		em.persist(usuario);
	}

	@Override
	public void altera(Usuario usuario) {
		em.merge(usuario);
	}

	@Override
	public void remove(Usuario usuario) {
		em.remove(em.merge(usuario));
	}

	@Override
	public List<Usuario> listaTodos() {
		CriteriaQuery<Usuario> cq = em.getCriteriaBuilder().createQuery(Usuario.class);
		
		cq.select(cq.from(Usuario.class));
		
		return em.createQuery(cq).getResultList();
	}

}
