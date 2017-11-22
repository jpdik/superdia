package br.com.interfacebean;

import java.util.List;

import br.com.modelo.Produto;

public interface IProduto {
	void adiciona(Produto produto);
	void altera(Produto produto);
	void remove(Produto produto);
	List<Produto> listaTodos();
}
