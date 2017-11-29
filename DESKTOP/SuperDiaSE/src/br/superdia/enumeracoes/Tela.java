package br.superdia.enumeracoes;

public enum Tela {
	LOGIN("login"),
	CAIXA("caixa"),
	PAGAMENTO("pagamento");
	
	private String tela;
	
	private Tela(String tela) {
		this.tela = tela;
	}

	/**
	 * Retorna um <i>tela</i> <code>String</code> com o valor da constante.
	 * @return um <i>tea</i> <code>String</code> representado uma janela da aplicação.
	 * */
	public String getTela() {
		return tela;
	}	

	/**
	 * Atribui um valor a constante.
	 * @param tela <code>String</code> que representa uma tela da aplicação.
	 * */
	public void setTela(String tela) {
		this.tela = tela;
	}	
}


