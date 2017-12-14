package br.com.mb;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.interfacebean.ICliente;
import br.com.modelo.Usuario;

@ManagedBean
@ViewScoped
public class ClienteMB {
	Usuario usuario = new Usuario();
	
	@EJB
	private ICliente iCliente;
	
	public String registra() {
		if(iCliente.autoAdiciona(usuario)) {
			return "login?faces-redirect=true";
		}
		usuario = new Usuario();
		return "registrar?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
