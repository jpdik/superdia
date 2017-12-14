package br.com.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.interfacebean.IProduto;
import br.com.modelo.Produto;

@ManagedBean
@SessionScoped
public class ProdutoMB {
	Produto produto = new Produto();

	@EJB
	private IProduto iProduto;

	public void gravar() {
		if (produto.getId() == null)
			iProduto.adiciona(produto);
		else
			iProduto.altera(produto);

		produto = new Produto();
	}

	public void cancela() {
		produto = new Produto();
	}

	public void remove(Produto produto) {
		iProduto.remove(produto);
	}

	public List<Produto> getProdutos() {
		return iProduto.listaTodos();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
