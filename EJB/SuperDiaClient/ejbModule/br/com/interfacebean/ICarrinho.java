package br.com.interfacebean;

import java.util.List;

import br.com.modelo.ItemVenda;

public interface ICarrinho {
	boolean adiciona(ItemVenda itemVenda);
	boolean remove(ItemVenda itemVenda);
	List<ItemVenda> listaTodos();
	void limpa();
	boolean finalizaCompra();
}
