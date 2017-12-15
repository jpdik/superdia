package br.com.sb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import br.com.interfacebean.ICarrinho;
import br.com.interfacebean.IProduto;
import br.com.interfacebean.IVenda;
import br.com.modelo.ItemVenda;
import br.com.modelo.Produto;
import br.com.modelo.Usuario;
import br.com.modelo.Venda;

@Stateful
@Remote(ICarrinho.class)
public class CarrinhoBean implements ICarrinho{
	
	@EJB
	private IProduto iProduto;
	
	@EJB
	private IVenda iVenda;
	
	private Set<ItemVenda> itensVenda = new HashSet<>();
	
	@Override
	public boolean adiciona(ItemVenda itemVenda) {
		int qtdeProduto = itemVenda.getProduto().getQuantidadeEstoque();
		
		// Verifica quantidade adicionada e estoque do produto são maiores que zero
		if (itemVenda.getQuantidade() <= 0 || qtdeProduto <= 0)
			return false;
		
		// Verifica se o carrinho já contém o produto
		boolean produtoNoCarrinho = itensVenda.contains(itemVenda);		
		if(produtoNoCarrinho){
			for(ItemVenda i: itensVenda) {
				if (i.equals(itemVenda)) {
					itemVenda.setQuantidade(i.getQuantidade() + itemVenda.getQuantidade());
					break;
				}
					
			}
		}
		
		// Verifica se há produto suficiente no estoque, caso não tenha retorna false
		if (!verificaEstoque(itemVenda)) return false;
		
		if (produtoNoCarrinho) {
			return itensVenda.remove(itemVenda) && itensVenda.add(itemVenda);
		}else
			return itensVenda.add(itemVenda);
	}
	
	@Override
	public boolean altera(ItemVenda itemVenda) {
		
		if (itensVenda.contains(itemVenda) && verificaEstoque(itemVenda)) {
			return itensVenda.remove(itemVenda) && itensVenda.add(itemVenda);
		}
		
		return false;
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
	public boolean finalizaCompra(Usuario usuario) {
		atualizaEstoque();
		
		// Registra um nova venda
		Venda venda = new Venda();
		venda.setData(Calendar.getInstance());
		venda.setItensVenda(listaTodos());
		venda.setUsuario(usuario);

		iVenda.adiciona(venda);
		
		// Limpa carrinho
		limpa();
		return true;
	}
	
	/*
	 * Ao finalizar a compra debita a quantidade em estoque de cada produto
	 */
	private void atualizaEstoque() {
		for(ItemVenda itemVenda: itensVenda) {
			int quantidade = itemVenda.getQuantidade();
			
			// Busca o produto no estoque
			Produto produto = iProduto.buscaPorID(itemVenda.getProduto().getId());
			
			// Caso o produto não esteja no estoque significa que é um produto de terceiros
			if(produto == null) continue;
			
			// Atualiza estoque
			produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
			iProduto.altera(produto);
		}
	}
	
	/**
	 * Verifica se possue produto suficiente no estoque
	 * @param itemVenda o item a ser verificado no estoque
	 * @return <b>true</b> se a operação foi realizada com sucesso. Caso contrário <b>false</b>.
	 */
	private boolean verificaEstoque(ItemVenda itemVenda) {
		
		int qtdeProduto = itemVenda.getProduto().getQuantidadeEstoque();
		
		// Verifica quantidade adicionada e estoque do produto são maiores que zero
		if (itemVenda == null || itemVenda.getQuantidade() <= 0 || qtdeProduto <= 0)
			return false;
		
		// Verifica se há produto suficiente no estoque, caso não tenha retorna false
		if (qtdeProduto < itemVenda.getQuantidade()) return false;
		
		return true;
	}
}
