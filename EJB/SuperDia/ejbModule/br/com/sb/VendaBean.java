package br.com.sb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import br.com.interfacebean.IVenda;
import br.com.modelo.Usuario;
import br.com.modelo.Venda;

@Stateless
@Remote(IVenda.class)
public class VendaBean implements IVenda {

	@PersistenceContext(name = "superdia")
	EntityManager em;

	@Override
	public boolean adiciona(Venda venda) {
		try {
			em.persist(venda);
			return true;
		} catch (EntityExistsException | IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean remove(Venda venda) {
		try {
			em.remove(em.merge(venda));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public List<Venda> listaTodos() {
		CriteriaQuery<Venda> vendaQuery = em.getCriteriaBuilder().createQuery(Venda.class);

		vendaQuery.select(vendaQuery.from(Venda.class));

		return em.createQuery(vendaQuery).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Venda> listaDoUsuario(Usuario usuario) {
		if (usuario != null) {
			Query q = em.createQuery("SELECT v FROM Venda v WHERE v.usuario.id = :id");

			q.setParameter("id", usuario.getId());

			return q.getResultList();
		}
		return null;
	}
}
