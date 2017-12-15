package br.com.mb;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.interfacebean.IAutentica;
import br.com.interfacebean.IUsuario;
import br.com.modelo.Cartao;
import br.com.modelo.Usuario;

@ManagedBean
@SessionScoped
public class LoginMB {
	Usuario usuario = new Usuario();
	
	@EJB
	private IUsuario iUsuario;
	private IAutentica iAutentica;
	
	public String adiciona() {
		iUsuario.adiciona(usuario);
		return "login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String autentica() {
		Usuario usuario = iAutentica.autentica(this.usuario);
		
		if(usuario != null) {
			if(usuario.getPerfil().equals(Usuario.perfis.Administrador.toString()) || usuario.getPerfil().equals(Usuario.perfis.Caixa.toString())) {
				this.usuario = new Usuario();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Autenticação", "Somente Clientes. Cadastre-se."));
				return "#";
			}
			
			this.usuario = usuario;
			return "menu?faces-redirect=true";
		}
		else {
			this.usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Autenticação", "Usuário ou senha inválidos"));
			return "#";
		}
	}
	
	public CartaoMB carregaCartaoAtivo() {		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(false);
		return (CartaoMB) sessao.getAttribute("cartaoMB");
    }
	
	public carrinhoMB carregaCarrinhoAtivo() {		
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) ctx.getExternalContext().getSession(false);
		return (carrinhoMB) sessao.getAttribute("carrinhoMB");
    }
	
	public String logout() {
		if(carregaCartaoAtivo() != null)
			carregaCartaoAtivo().card = new Cartao();
		if(carregaCarrinhoAtivo() != null)
			carregaCarrinhoAtivo().limpa();
		usuario = new Usuario();
		return "login.xhtml?faces-redirect=true";
	}
}
