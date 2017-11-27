package br.com.interfacebean;

import br.com.modelo.Usuario;

public interface ICliente {
	/**
	 * cliente se auto adiciona no sistema, o papel é definido como cliente antes de ir para o banco, não necessário cadastrar.
	 * 
	 * @param usuario - Objeto usuario com usuario e senha que será cadastrado.
	 * 
	 * @return <b>True</b> se foi cadastrado com sucesso ou <b>False</b> caso contrário.
	*/
	boolean autoAdiciona(Usuario usuario);
	
	/**
	 * cliente se auto altera no sistema, o papel é definido como cliente antes de ir para o banco, não necessário alterar.
	 * 
	 * @param usuario - Objeto usuario com usuario e senha que será alterado.
	 * 
	 * @return <b>True</b> se foi alterado com sucesso ou <b>False</b> caso contrário.
	*/
	boolean autoAltera(Usuario usuario);
	
	/**
	 * cliente se auto remove no sistema.
	 * 
	 * @param usuario - Objeto usuario com usuario e senha que será removido.
	 * 
	 * @return <b>True</b> se foi removido com sucesso ou <b>False</b> caso contrário.
	*/
	boolean autoRemove(Usuario usuario);
}
