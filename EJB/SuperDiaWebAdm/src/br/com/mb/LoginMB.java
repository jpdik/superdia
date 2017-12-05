package br.com.mb;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.interfacebean.IAutentica;
import br.com.interfacebean.IUsuario;
import br.com.modelo.Usuario;

@ManagedBean
@SessionScoped
public class LoginMB {
	Usuario usuario = new Usuario();
	
	@EJB
	private IUsuario iUsuario;
	
	@EJB
	private IAutentica iAutentica;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String autentica() {
		Usuario usuario = iAutentica.autentica(this.usuario);
		
		if(usuario != null && usuario.getPerfil().equals(Usuario.perfis.Administrador.toString())) {
			this.usuario = usuario;
			return "menu?faces-redirect=true";
		}
		else {
			this.usuario = new Usuario();
			return "login?faces-redirect=true";
		}
	}
	
	public String logout() {
		usuario = new Usuario();
		return "login?faces-redirect=true";
	}
}
