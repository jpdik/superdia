package br.com.sb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.interfacebean.ICliente;
import br.com.modelo.Usuario;

@Stateless
@Remote(ICliente.class)
public class ClienteBean implements ICliente {
	@PersistenceContext(name="superdia")
	EntityManager em;
	
	@Override
	public boolean autoAdiciona(Usuario usuario) {
		if(!entityExists(usuario)) {
			usuario.setPerfil(Usuario.perfis.Cliente.name());
			em.persist(usuario);
			return true;
		}
		return false;
	}

	@Override
	public boolean autoAltera(Usuario usuario) {
		if(entityExists(usuario)) {
			em.merge(usuario);
			return true;
		}
		return false;
	}

	@Override
	public boolean autoRemove(Usuario usuario) {
		if(entityExists(usuario)) {
			em.remove(em.merge(usuario));
			return true;
		}
		return false;
	}
	
	private boolean entityExists(Usuario usuario) {
		try {
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario");
			
			q.setParameter("usuario", usuario.getUsuario());
			
			q.getSingleResult();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}
