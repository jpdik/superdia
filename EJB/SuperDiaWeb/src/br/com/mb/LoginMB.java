package br.com.mb;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.interfacebean.IAutentica;
import br.com.interfacebean.IUsuario;
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
			this.usuario = usuario;
			return "menu?faces-redirect=true";
		}
		else {
			this.usuario = new Usuario();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Autenticação", "Usuário ou senha inválidos"));
			return "#";
		}
	}
	
	public String logout() {
		usuario = new Usuario();
		return "login.xhtml?faces-redirect=true";
	}
}
