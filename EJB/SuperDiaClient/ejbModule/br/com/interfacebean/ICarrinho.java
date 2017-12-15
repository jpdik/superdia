package br.com.interfacebean;

import java.util.List;

import br.com.modelo.ItemVenda;
import br.com.modelo.Usuario;

public interface ICarrinho {
	/**
	 * Adiciona um item ao carrinho. Somente será permitido realizar esta operação
	 * caso possua item suficientes no estoque. Caso não possua o produto não será adicionado. 
	 * @param itemVenda o item a ser adicionado no carrinho.
	 * @return <b>true</b> se a operação foi realizada com sucesso, caso contrário retorna <b>false</b>.
	 */
	boolean adiciona(ItemVenda itemVenda);
	
	/**
	 * Altera a quantidade do produto presente no carrinho.
	 * @param produto produto a ser alterado.
	 * @param quantidade nova quantidade a ser introduzida.
	 * @return <b>true</b> se a operação foi realizada com sucesso, caso contrário retorna <b>false</b>.
	 */
	boolean altera(ItemVenda itemVenda);
	
	/**
	 * Remove um item do carrinho.
	 * @param itemVenda o item a ser removido do carrinho.
	 * @return <b>true</b> se a operação foi realizada com sucesso. Caso contrário <b>false</b>.
	 */
	boolean remove(ItemVenda itemVenda);
	
	/**
	 * Lista todos itens presente no carrinho.
	 * @return uma lista com todos itens do carrinho.
	 */
	List<ItemVenda> listaTodos();
	
	/**
	 * Limpa o carrinho.
	 */
	void limpa();
	
	/**
	 * Finaliza uma compra. Ao finalizar, os produtos presentes no carrinho serão
	 * atualizados no estoque.
	 * @return <b>true</b> se a operação foi realizada com sucesso. Caso contrário <b>false</b>.
	 */
	boolean finalizaCompra(Usuario usuario);
}
