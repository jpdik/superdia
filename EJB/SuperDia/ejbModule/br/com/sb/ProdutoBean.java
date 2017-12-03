package br.com.sb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import br.com.interfacebean.IProduto;
import br.com.modelo.Produto;

@Stateless
@Remote(IProduto.class)
public class ProdutoBean implements IProduto {
	
	@PersistenceContext(name="superdia")
	EntityManager em;

	@Override
	public boolean adiciona(Produto produto) {
		try {
			em.persist(produto);
			return true;
		} catch (EntityExistsException | IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean altera(Produto produto) {
		try {
			em.merge(produto);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
		
	}

	@Override
	public boolean remove(Produto produto) {
		try {
			em.remove(em.merge(produto));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
		
	}
	
	@Override
	public List<Produto> listaTodos() {
		CriteriaQuery<Produto> produtoQuery = em.getCriteriaBuilder().createQuery(Produto.class);
		
		produtoQuery.select(produtoQuery.from(Produto.class));
		
		return em.createQuery(produtoQuery).getResultList();
	}	
}
