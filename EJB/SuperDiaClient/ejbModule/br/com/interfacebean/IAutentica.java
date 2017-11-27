package br.com.interfacebean;

import br.com.modelo.Usuario;

public interface IAutentica {
	/**
	 * Autentica um usuário no sistema.
	 * 
	 * @param usuario - Objeto usuario com usuario e senha que será autenticado.
	 * 
	 * @return null se o usuario não existe ou não está cadastrado ou um objeto do usuário somente com seu usuario e papel.
	*/
	Usuario autentica(Usuario usuario);
}
