package br.com.sb;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.interfacebean.IAutentica;
import br.com.modelo.Usuario;

@Stateless
@Remote(IAutentica.class)
public class AutenticaBean implements IAutentica {
	@PersistenceContext(name="superdia")
	EntityManager em;
	
	@Override
	public Usuario autentica(Usuario usuario) {
		try{
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.senha = :senha");
			
			q.setParameter("usuario", usuario.getUsuario());
			q.setParameter("senha", usuario.getSenha());
		
		
			return (Usuario) q.getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

}
