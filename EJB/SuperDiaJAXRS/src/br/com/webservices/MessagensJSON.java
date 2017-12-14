package br.com.webservices;

/**
 * @author Deborah
 */
public enum MessagensJSON {
	//JSON Usuário
	ERRO_USUARIO_INVALIDO("{'cod' : 401, 'msg':'Usuário não tem permissão para realizar esta operação.'}"),
	USUARIO_CADASTRADO_SUCESSO("{'cod': 200, 'msg': 'Cadastro realizado com sucesso!'}"),
	USUARIO_CADASTRADO_FALHA("{'cod': 401, 'msg': 'Falha no Cadastro(usuário já existente).'}"),
	ALTERAR_USUARIO_SUCESSO("{'cod' : 200, 'msg':'Informações alteradas com sucesso!'}"), 
	USUARIO_INALTERADO("{'cod' : 401, 'msg':'Usuário já existente. (Inalterado)'}"),
	ALTERAR_USUARIO_FALHA("{'cod' : 401, 'msg':'Usuário que deseja alterar não existe, ou informações não coincidem.'}"),
	INFORMACAO_ALTERADA_SUCESSO("{'cod' : 200, 'msg':'Informações alteradas com sucesso!'}"), 
	USUARIO_REMOVIDO_SUCESSO("{'cod' : 200, 'msg':'Usuário removido com sucesso!'}"), 
	USUARIO_NAO_EXISTE("{'cod' : 401, 'msg':'Usuário removido com sucesso!'}");
	
	
	
	private MessagensJSON(String mensagem) {
		this.mensagem = mensagem;
	}

	private String mensagem;

	public String getMensagem() {
		return mensagem;
}
	
	
}
