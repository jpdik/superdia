package br.com.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.interfacebean.ICarrinho;
import br.com.interfacebean.IProduto;
import br.com.modelo.ItemVenda;
import br.com.modelo.Produto;
import br.com.modelo.Usuario;

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
	
	/*
	 * Permite recuperar o usuário ativo da sessão. Esse usuário e armazenado ao realizar login
	 */
	public Usuario carregaUsuarioAtivo() {		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(false);
		return ((LoginMB) sessao.getAttribute("loginMB")).usuario;
    }
	
	public String finalizaCompra() {
		Usuario usuario = carregaUsuarioAtivo();
		
		// Caso tenha usuário ativo na sessão finaliza compra, 
		// caso contrário redireciona para página de login. Após realizar o login o usuário
		// pode concluir a compra.
		if (usuario.getId() != null) {
			iCarrinho.finalizaCompra(usuario);
			return "";
		}else
			return "login";
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
