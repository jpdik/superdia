package br.com.webservices;

public enum MessagensJSON {
	//JSON Usuário
	ERRO_USUARIO_INVALIDO("{'cod' : 401, 'msg':'Usuário não tem permissão para realizar esta operação.'}");
	
	private MessagensJSON(String mensagem) {
		this.mensagem = mensagem;
	}

	private String mensagem;

	public String getMensagem() {
		return mensagem;
}
	
	
}
