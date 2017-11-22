package br.com.sb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.interfacebean.IProduto;
import br.com.modelo.Produto;

@Stateless
@Remote(IProduto.class)
public class ProdutoBean implements IProduto {
	
	@PersistenceContext(name="superdia")
	EntityManager em;

	@Override
	public void adiciona(Produto produto) {
		em.persist(produto);
	}

	@Override
	public void altera(Produto produto) {
		em.merge(produto);
	}

	@Override
	public void remove(Produto produto) {
		em.remove(em.merge(produto));
	}
	
	@Override
	public List<Produto> listaTodos() {
		Query query = em.createQuery("SELECT p FROM Produto p");
		
		return query.getResultList();
	}	
}
