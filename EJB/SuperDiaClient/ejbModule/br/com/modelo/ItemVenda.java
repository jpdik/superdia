package br.com.modelo;

import java.util.Objects;

public class ItemVenda{

	private Long id;
	private Produto produto;
	private int quantidade;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public boolean equals(Object obj) {
		ItemVenda itemVendaObj = (ItemVenda) obj;
		return itemVendaObj.getProduto().getId() == getProduto().getId();
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(produto.getId(), produto.getNome(), produto.getDescricao());
    }
}
