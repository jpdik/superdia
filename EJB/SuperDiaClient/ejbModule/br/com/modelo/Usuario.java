package br.com.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {
	public enum perfis {
        Cliente, Caixa, Administrador;
   } 
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String usuario;
	private String senha;
	private String perfil;
	
	public Usuario() {
	}

	public Usuario(String usuario, String senha, String perfil) {
		this.usuario = usuario;
		this.senha = senha;
		this.perfil = perfil;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(Usuario.perfis perfil) {
		for(Usuario.perfis i : Usuario.perfis.values())
			if(perfil.equals(i)){
				this.perfil = perfil.toString();
				return;
			}
		this.perfil = perfis.Cliente.toString();
	}
	
	@Override
	public String toString() {
		return String.format("usuario: %s, senha: %s, perfil: %s", usuario, senha, perfil);
	}
}
