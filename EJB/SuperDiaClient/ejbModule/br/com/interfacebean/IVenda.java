package br.com.interfacebean;

import java.util.List;

import br.com.modelo.Venda;

public interface IVenda {
	/**
	 * Persiste uma venda efetuada.
	 * @param venda a venda a ser persistida.
	 * @return <b>true</b> se foi adicionado com sucesso ou <b>false</b> caso contrário.
	 */
	boolean adiciona(Venda venda);
	
	/**
	 * Remove uma venda persistida no banco de dados.
	 * @param venda a venda a ser removida.
	 * @return <b>true</b> se foi removido com sucesso ou <b>false</b> caso contrário.
	 */
	boolean remove(Venda venda);
	
	/**
	 * Lista todas vendas efetuadas e persistidas no banco de dados.
	 * @return um <b>List</b> com todas vendas efetuadas.
	 */
	List<Venda> listaTodos();
}
