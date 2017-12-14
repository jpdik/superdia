package br.com.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
	@EJB
	private IProduto iProduto;
	
	@EJB
	private ICarrinho iCarrinho;
	
	public void adicionar(Produto produto) {
		ItemVenda itemVenda = new ItemVenda();
		
		itemVenda.setProduto(produto);
		itemVenda.setQuantidade(1);
		
		iCarrinho.adiciona(itemVenda);
		
		FacesContext context = FacesContext.getCurrentInstance();
        
        context.addMessage(null, new FacesMessage("Carrinho",  produto.getNome()+" foi adicionado ao carrinho!"));
	}
	
	public List<ItemVenda> produtosCarrinho(){
		return iCarrinho.listaTodos();
	}
	
	/*
	 * Permite recuperar o usuário ativo da sessão. Esse usuário e armazenado ao realizar login
	 */
	public Usuario carregaUsuarioAtivo() {		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(false);
		return ((LoginMB) sessao.getAttribute("loginMB")).usuario;
    }
	
	public CartaoMB carregaCartaoAtivo() {		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(false);
		return (CartaoMB) sessao.getAttribute("cartaoMB");
    }
	
	public String finalizaCompra() {
		Usuario usuario = carregaUsuarioAtivo();

		// Caso tenha usuário ativo na sessão finaliza compra, 
		// caso contrário redireciona para página de login. Após realizar o login o usuário
		// pode concluir a compra.
		
		System.out.println(carregaCartaoAtivo());
		
		if (usuario != null && usuario.getId() != null && carregaCartaoAtivo().analisaCartao()) {
			iCarrinho.finalizaCompra(usuario);
			
			return "sucesso?faces-redirect=true";
		}else
			return "login?faces-redirect=true";
	}
	
	public void remove(ItemVenda itemVenda) {
		iCarrinho.remove(itemVenda);
	}
	
	public List<ItemVenda> getItemVendas() {
		return iCarrinho.listaTodos();
	}
}
