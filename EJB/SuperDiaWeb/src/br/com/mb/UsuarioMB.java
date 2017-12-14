package br.com.mb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.interfacebean.IUsuario;
import br.com.modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioMB {
	@EJB
	private IUsuario iUsuario;
	
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public void cancela(){
		this.usuario = new Usuario();
	}
	
	public List<Usuario> getUsuarios() {
		List<Usuario> usuarios = iUsuario.listaTodos();
		Collections.reverse(usuarios);
		return usuarios;		
	}
	
	public void grava(){
		if(!usuario.getSenha().isEmpty()) {
			if(usuario.getId() == null) {
				iUsuario.adiciona(usuario);
			}
			else
				iUsuario.altera(usuario);
			cancela();
		}
	}
	
	public List<String> getPerfis() {
		List<String> perfis = new ArrayList<>();
		for(Usuario.perfis perfil : Usuario.perfis.values())
			perfis.add(perfil.name());
		return perfis;
	}
	
	public void remove(Usuario usuario){
		iUsuario.remove(usuario);
	}
}

