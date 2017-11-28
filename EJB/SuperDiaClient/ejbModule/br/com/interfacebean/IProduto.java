package br.com.interfacebean;

import java.util.List;

import br.com.modelo.Produto;

public interface IProduto {
	/**
	 * Adiciona um produto ao estoque.
	 * @param produto o produto a ser adicionado ao estoque.
	 */
	void adiciona(Produto produto);
	
	/**
	 * Altera um produto no estoque.
	 * @param produto o produto a ser alterado no estoque.
	 */
	void altera(Produto produto);
	
	/**
	 * Remove um produto do estoque. 
	 * @param produto o produto a ser removido do estoque.
	 */
	void remove(Produto produto);
	
	/**
	 * Lista todos produtos do estoque.
	 * @return um <b>List</b> com todos produtos do estoque.
	 */
	List<Produto> listaTodos();
}
