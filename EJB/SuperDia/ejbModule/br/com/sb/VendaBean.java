package br.com.sb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.interfacebean.IVenda;
import br.com.modelo.Venda;

@Stateless
@Remote(IVenda.class)
public class VendaBean implements IVenda {
	
	@PersistenceContext(name="superdia")
	EntityManager em;

	@Override
	public void adiciona(Venda venda) {
		em.persist(venda);
	}

	@Override
	public void remove(Venda venda) {
		em.remove(em.merge(venda));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Venda> listaTodos() {
		Query query = em.createQuery("SELECT v FROM Venda v");
		
		return query.getResultList();
	}	
}
