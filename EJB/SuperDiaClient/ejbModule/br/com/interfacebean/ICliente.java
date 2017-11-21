package br.com.interfacebean;

import br.com.modelo.Usuario;

public interface ICliente {
	void autoAdiciona(Usuario usuario);
	void autoAltera(Usuario usuario);
	void autoRemove(Usuario usuario);
}
