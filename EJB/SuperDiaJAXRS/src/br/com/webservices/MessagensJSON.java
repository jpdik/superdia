package br.com.webservices;

/**
 * @author Deborah
 */
public enum MessagensJSON {
	//JSON Usuário
	ERRO_USUARIO_INVALIDO("{'cod' : 401, 'msg':'Usuário não tem permissão para realizar esta operação.'}"),
	USUARIO_CADASTRADO_SUCESSO("{'cod': 200, 'msg': 'Cadastro realizado com sucesso!'}"),
	USUARIO_CADASTRADO_FALHA("{'cod': 401, 'msg': 'Não foi possível cadastrar usuário.'}");
	
	
	private MessagensJSON(String mensagem) {
		this.mensagem = mensagem;
	}

	private String mensagem;

	public String getMensagem() {
		return mensagem;
}
	
	
}
