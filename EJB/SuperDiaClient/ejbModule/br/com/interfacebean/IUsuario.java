package br.com.interfacebean;

import java.util.List;

import br.com.modelo.Usuario;

public interface IUsuario {
	/**
	 * adiciona um usuário no sistema
	 * 
	 * @param usuario - Objeto usuario com usuario, senha e papel que será cadastrado.
	 * 
	 * @return <b>True</b> se foi cadastrado com sucesso ou <b>False</b> caso contrário.
	*/
	boolean adiciona(Usuario usuario);
	
	/**
	 * altera um usuário no sistema
	 * 
	 * @param usuario - Objeto usuario com usuario, senha e papel que será alterado.
	 * 
	 * @return <b>True</b> se foi alterado com sucesso ou <b>False</b> caso contrário.
	*/
	boolean altera(Usuario usuario);
	
	/**
	 * remove um usuário no sistema
	 * 
	 * @param usuario - Objeto usuario com usuario que será removido.
	 * 
	 * @return <b>True</b> se foi removido com sucesso ou <b>False</b> caso contrário.
	*/
	boolean remove(Usuario usuario);
	
	/**
	 * lista todos os usuário do sistema.
	 * 
	 * @return uma List com todos os usuário cadastrados no banco.
	*/
	List<Usuario> listaTodos();
}
