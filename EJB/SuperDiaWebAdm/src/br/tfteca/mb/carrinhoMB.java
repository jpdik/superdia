package br.tfteca.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.interfacebean.ICarrinho;
import br.com.interfacebean.IProduto;
import br.com.modelo.ItemVenda;
import br.com.modelo.Produto;

@ManagedBean
@SessionScoped
public class carrinhoMB {
	private ItemVenda itemVenda = new ItemVenda();
	private long idProduto;
	
	@EJB
	private IProduto iProduto;
	
	@EJB
	private ICarrinho iCarrinho;
	
	public void adiciona() {
		
		// Busca produto
		for(Produto produto: getProdutos())
			if (produto.getId() == idProduto) {
				itemVenda.setProduto(produto);
				break;
			}
				
		iCarrinho.adiciona(itemVenda);
		
		// Limpa o item após gravar
		itemVenda = new ItemVenda();
	}
	
	public void finalizaCompra() {
		iCarrinho.finalizaCompra();
	}
	
	public void remove(ItemVenda itemVenda) {
		iCarrinho.remove(itemVenda);
	}
	
	public List<ItemVenda> getItemVendas() {
		return iCarrinho.listaTodos();
	}
	
	public List<Produto> getProdutos(){
		return iProduto.listaTodos();
	}

	public ItemVenda getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
}
