package br.com.mb;

import java.util.ArrayList;
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
	
	String filtro = new String();
	
	@EJB
	private IProduto iProduto;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutosInternos() {
		List<Produto> produtos = new ArrayList<>();
		List<Produto> lista = iProduto.listaTodos();
		if(filtro.isEmpty())
			return lista;
		
		for(Produto p : lista)
			if(p.getNome().contains(filtro))
				produtos.add(p);
		return produtos;
	}
	
	public List<Produto> getProdutosExternos() {
		return iProduto.listaTodosExternos(filtro);
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	public String busca() {
		return "menu?faces-redirect=true";
	}
	
	public String limpaBusca() {
		filtro = new String();
		return "menu?faces-redirect=true";
	}
	
}
