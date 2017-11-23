package br.com.sb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import br.com.interfacebean.ICarrinho;
import br.com.interfacebean.IProduto;
import br.com.modelo.ItemVenda;
import br.com.modelo.Produto;

@Stateful
@Remote(ICarrinho.class)
public class CarrinhoBean implements ICarrinho{
	
	@EJB
	private IProduto iProduto;
	
	private Set<ItemVenda> itensVenda = new HashSet<>();
	
	@Override
	public boolean adiciona(ItemVenda itemVenda) {
		int qtdeProduto = itemVenda.getProduto().getQuantidadeEstoque();
		
		// Verifica se há produto suficiente no estoque, caso não tenha retorna false
		if (itemVenda.getQuantidade() <= 0 || qtdeProduto <= 0 ||
				qtdeProduto < itemVenda.getQuantidade())
			
			return false;
		
		return itensVenda.add(itemVenda);
	}

	@Override
	public boolean remove(ItemVenda itemVenda) {
		return itensVenda.remove(itemVenda);
	}

	@Override
	public List<ItemVenda> listaTodos() {
		return new ArrayList<>(itensVenda);
	}

	@Override
	public void limpa() {
		itensVenda.clear();
	}

	@Override
	public boolean finalizaCompra() {
		atualizaEstoque();
		
		// Limpa carrinho
		limpa();
		return true;
	}
	
	/*
	 * Ao finalizar a compra debita a quantidade em estoque de cada produto
	 */
	private void atualizaEstoque() {
		for(ItemVenda itemVenda: itensVenda) {
			Produto produto = itemVenda.getProduto();
			produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - itemVenda.getQuantidade());
			
			iProduto.altera(produto);
		}
	}
}
