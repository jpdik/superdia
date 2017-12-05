package br.com.sb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
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
	public boolean autoAdiciona(Usuario usuario) {
		try {
			usuario.setPerfil(Usuario.perfis.Cliente.name());
			em.persist(usuario);
			return true;
		} catch (EntityExistsException e) {
			return false;
		}
	}

	@Override
	public boolean autoAltera(Usuario usuario) {
		try {
			usuario.setPerfil(Usuario.perfis.Cliente.name());
			em.merge(usuario);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean autoRemove(Usuario usuario) {
		try {
			em.remove(em.merge(usuario));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

}
