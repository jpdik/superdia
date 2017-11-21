package br.com.interfacebean;

import java.util.List;

import br.com.modelo.Usuario;

public interface IUsuario {
	void adiciona(Usuario usuario);
	void altera(Usuario usuario);
	void remove(Usuario usuario);
	List<Usuario> listaTodos();
}
