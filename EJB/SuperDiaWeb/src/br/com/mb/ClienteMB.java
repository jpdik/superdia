package br.com.mb;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro", "Usuário já existente."));
		return "#";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
