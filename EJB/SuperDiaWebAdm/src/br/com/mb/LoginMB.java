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
	
	public String adiciona() {
		iUsuario.adiciona(usuario);
		return "login?redirect-faces=true";
	}

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
			return "menu.xhtml?redirect-faces=true";
		}
		else {
			this.usuario = new Usuario();
			return "login";
		}
	}
	
	public String logout() {
		usuario = new Usuario();
		return "login";
	}
}
