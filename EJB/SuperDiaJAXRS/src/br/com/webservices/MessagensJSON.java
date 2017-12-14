package br.com.webservices;

/**
 * @author Deborah
 */
public enum MessagensJSON {
	//JSON Usuário
	ERRO_USUARIO_INVALIDO("{'cod' : 200, 'msg':'Usuário não tem permissão para realizar esta operação.', 'usuario':{%s}}"),
	USUARIO_CADASTRADO_SUCESSO("{'cod': 200, 'msg': 'Cadastro realizado com sucesso!'}"),
	USUARIO_CADASTRADO_FALHA("{'cod': 200, 'msg': 'Falha no Cadastro(usuário já existente).'}"),
	ALTERAR_USUARIO_SUCESSO("{'cod' : 200, 'msg':'Informações alteradas com sucesso!'}"), 
	USUARIO_INALTERADO("{'cod' : 200, 'msg':'Usuário já existente. (Inalterado)'}"),
	ALTERAR_USUARIO_FALHA("{'cod' : 200, 'msg':'Usuário que deseja alterar não existe, ou informações não coincidem.'}"),
	INFORMACAO_ALTERADA_SUCESSO("{'cod' : 200, 'msg':'Informações alteradas com sucesso!'}"), 
	USUARIO_REMOVIDO_SUCESSO("{'cod' : 200, 'msg':'Usuário removido com sucesso!'}"), 
	USUARIO_NAO_EXISTE("{'cod' : 200, 'msg':'Usuário removido com sucesso!'}"),
	RETORNA_USUARIO("{'cod': 200, 'msg': 'Login realizado com sucesso!', 'usuario':{%s}}"),
	
	//JSON produto
	PRODUTO_CADASTRADO_SUCESSO("{'cod': 200, 'msg': 'Produto cadastrado com sucesso!'}"),
	PRODUTO_CADASTRADO_FALHA("{'cod': 200, 'msg': 'Falha ao cadastrar o procuto!'}");
	
	
	
	private MessagensJSON(String mensagem) {
		this.mensagem = mensagem;
	}

	private String mensagem;

	public String getMensagem() {
		return mensagem;
}
	
	
}
