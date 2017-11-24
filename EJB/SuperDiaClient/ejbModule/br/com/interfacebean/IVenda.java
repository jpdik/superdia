package br.com.interfacebean;

import java.util.List;

import br.com.modelo.Venda;

public interface IVenda {
	void adiciona(Venda venda);
	void remove(Venda venda);
	List<Venda> listaTodos();
}
