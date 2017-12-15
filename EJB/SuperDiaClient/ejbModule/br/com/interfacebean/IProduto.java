package br.com.interfacebean;

import java.util.List;

import br.com.modelo.Produto;

public interface IProduto {
	/**
	 * Adiciona um produto ao estoque.
	 * @param produto o produto a ser adicionado ao estoque.
	 * @return <b>true</b> se foi adicionado com sucesso ou <b>false</b> caso contrário.
	 */
	boolean adiciona(Produto produto);
	
	/**
	 * Altera um produto no estoque.
	 * @param produto o produto a ser alterado no estoque.
	 * @return <b>true</b> se foi alterado com sucesso ou <b>talse</b> caso contrário.
	 */
	boolean altera(Produto produto);
	
	/**
	 * Remove um produto do estoque. 
	 * @param produto o produto a ser removido do estoque.
	 * @return <b>true</b> se foi removido com sucesso ou <b>talse</b> caso contrário.
	 */
	boolean remove(Produto produto);
	
	/**
	 * Lista todos produtos do estoque.
	 * @return um <b>List</b> com todos produtos do estoque.
	 */
	List<Produto> listaTodos();
	/**
	 * Lista todos produtos externos.
	 * @param filtro Lista somente os produtos que contenham no filtro o mesmo nome e, se for vazio, lista todos.
	 * @return um <b>List</b> com todos produtos do estoque.
	 */
	List<Produto> listaTodosExternos(String filtro);
	
	/**
	 * Busca um produto pelo id
	 * @param id a id do produto a ser pesquisado
	 * @return o produto caso o mesmo exista ou null caso contrário.
	 */
	Produto buscaPorID(Long id);
}
