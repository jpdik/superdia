package br.com.interfacebean;

import java.util.List;

import br.com.modelo.Venda;

public interface IVenda {
	/**
	 * Persiste uma venda efetuada.
	 * @param venda a venda a ser persistida.
	 */
	void adiciona(Venda venda);
	
	/**
	 * Remove uma venda persistida no banco de dados.
	 * @param venda a venda a ser removida.
	 */
	void remove(Venda venda);
	
	/**
	 * Lista todas vendas efetuadas e persistidas no banco de dados.
	 * @return um <b>List</b> com todas vendas efetuadas.
	 */
	List<Venda> listaTodos();
}
