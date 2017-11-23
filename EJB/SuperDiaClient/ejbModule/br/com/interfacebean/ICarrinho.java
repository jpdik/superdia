package br.com.interfacebean;

import java.util.List;

import br.com.modelo.ItemVenda;

public interface ICarrinho {
	void adiciona(ItemVenda itemVenda);
	void remove(ItemVenda itemVenda);
	List<ItemVenda> listaTodos();
	void limpa();
	void finalizaCompra();
}
