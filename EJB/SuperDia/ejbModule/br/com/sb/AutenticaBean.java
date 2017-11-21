package br.com.sb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.interfacebean.IAutentica;
import br.com.modelo.Usuario;

@Stateless
@Remote(IAutentica.class)
public class AutenticaBean implements IAutentica {
	@PersistenceContext(name="superdia")
	EntityManager em;
	
	@Override
	public Usuario autentica(Usuario usuario) {
		return em.find(Usuario.class, usuario.getId());
	}

}
