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
	
	public void altera(ItemVenda itemVenda) {
		
		itemVenda.setQuantidade(10);
		
		iCarrinho.altera(itemVenda);
		
		FacesContext context = FacesContext.getCurrentInstance();
        
        context.addMessage(null, new FacesMessage("Carrinho",  itemVenda.getProduto().getNome()+" foi alterado no carrinho!"));
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
		// caso contráio redireciona para página de login. Após realizar o login o usuário
		// pode concluir a compra.
		
		System.out.println(carregaCartaoAtivo());
		
		if (usuario != null && usuario.getId() != null) {
			if(carregaCartaoAtivo().analisaCartao()) {
				iCarrinho.finalizaCompra(usuario);
				
				return "sucesso?faces-redirect=true";
			}
			else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Finalizar Compra", "O número do cartão é inválido"));
		        return "#";
			}
		}else
			return "login?faces-redirect=true";
	}
	
	public boolean carrinhoVazio() {
		return iCarrinho.listaTodos().size() > 0 ? false : true;
	}
	
	public void remove(ItemVenda itemVenda) {
		iCarrinho.remove(itemVenda);
	}
	
	public void limpa() {
		iCarrinho.limpa();
	}
	
	public void aumentaQuantidade(ItemVenda itemVenda) {
		itemVenda.setQuantidade(itemVenda.getQuantidade() + 1);
		if (!iCarrinho.altera(itemVenda)) {
			
			FacesContext context = FacesContext.getCurrentInstance();
	        
	        context.addMessage(null, new FacesMessage("Carrinho",  
	        		itemVenda.getProduto().getNome()+" esgotado no estoque!"));
		}
	}
	
	public void diminuiQuantidade(ItemVenda itemVenda) {
		if(itemVenda.getQuantidade() > 0) {
			itemVenda.setQuantidade(itemVenda.getQuantidade() - 1);
			iCarrinho.altera(itemVenda);
		}
	}
	
	public double valorTotal(ItemVenda itemVenda) {
		return itemVenda.getProduto().getPreco() * itemVenda.getQuantidade();
	}
	
	public List<ItemVenda> getItemVendas() {
		return iCarrinho.listaTodos();
	}
}
