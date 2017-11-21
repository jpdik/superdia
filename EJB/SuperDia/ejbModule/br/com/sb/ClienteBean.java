package br.com.sb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.interfacebean.ICliente;
import br.com.modelo.Usuario;

@Stateless
@Remote(ICliente.class)
public class ClienteBean implements ICliente {
	@PersistenceContext(name="superdia")
	EntityManager em;
	
	@Override
	public void autoAdiciona(Usuario usuario) {
		em.persist(usuario);
	}

	@Override
	public void autoAltera(Usuario usuario) {
		em.merge(usuario);
	}

	@Override
	public void autoRemove(Usuario usuario) {
		em.remove(em.merge(usuario));
	}

}
