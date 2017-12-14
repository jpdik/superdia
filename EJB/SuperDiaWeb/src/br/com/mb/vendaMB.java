package br.com.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.interfacebean.IVenda;
import br.com.modelo.ItemVenda;
import br.com.modelo.Usuario;
import br.com.modelo.Venda;

@ManagedBean
@SessionScoped
public class vendaMB {
	
	@EJB
	private IVenda iVenda;
	
	public void remove(Venda venda) {
		iVenda.remove(venda);
	}
	
	public Usuario carregaUsuarioAtivo() {		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(false);
		return ((LoginMB) sessao.getAttribute("loginMB")).usuario;
    }
	
	public List<Venda> getVendas() {
		return iVenda.listaDoUsuario(carregaUsuarioAtivo());
	}
	
	public double valorTotal(List<ItemVenda> itensVenda) {
		double total = 0;
		for(ItemVenda i: itensVenda) {
			total += i.getProduto().getPreco() * i.getQuantidade();
		}
		return total;
	}
}
