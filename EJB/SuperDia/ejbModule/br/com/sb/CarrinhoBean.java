package br.com.sb;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateful;

import br.com.interfacebean.ICarrinho;
import br.com.modelo.ItemVenda;

@Stateful
@Remote(ICarrinho.class)
public class CarrinhoBean implements ICarrinho{

	private Set<ItemVenda> itensVenda = new HashSet<>();
	
	@Override
	public void adiciona(ItemVenda itemVenda) {
		itensVenda.add(itemVenda);
	}

	@Override
	public void remove(ItemVenda itemVenda) {
		itensVenda.remove(itemVenda);
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
	public void finalizaCompra() {
		// Finaliza compra
	}
}
