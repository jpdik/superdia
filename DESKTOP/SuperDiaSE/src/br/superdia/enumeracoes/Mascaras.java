package br.superdia.enumeracoes;

public enum Mascaras {
	
	/**	CPF("(\\d{3}.){2}\\d{3}-\\d{2}"), // DDD.DDD.DDD-DD*/
	CPF("(\\d{3}.){2}\\d{3}-\\d{2}"), // DDD.DDD.DDD-DD
	
	//String email = "\\w+@\\w+\\.[a-z]{3}|\\w+@\\w+\\.[a-z]{3}\\.[a-z]{2}";// padrão para validação de e-mail
	/** EMAIL("\\w+([\\.-]?\\w)*\\w+@\\w+\\.[a-z]{3}|\\w+([\\.-]?\\w)*\\w+@\\w+\\.[a-z]{3}\\.[a-z]{2}"), // user@provedor.tipo ou user@provedor.tipo.pais*/
	EMAIL("\\w+([\\.-]?\\w)*\\w+@\\w+\\.[a-z]{3}|\\w+([\\.-]?\\w)*\\w+@\\w+\\.[a-z]{3}\\.[a-z]{2}"),
		
	/**NOME_COMPLETO("[A-Z][a-z]{3,20}(\\s[A-Z][a-z]{3,20})+"), exemplo: Fabiana Teixieira Soares, 
	 * os nomes sempre tem que iniciar com letra maiúscula*/
	NOME_COMPLETO("[A-ZÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇ][a-záàâãéèêíïóôõöúç]{2,20}(\\s[A-ZZÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇ][a-záàâãéèêíïóôõöúç]{1,20})+"),
	
	ENDERECO("[A-ZZÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇ,a-záàâãéèêíïóôõöúç]{2,20}(\\s[A-ZZÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇ,a-záàâãéèêíïóôõöúç,0-9]+)+"),
		
	/**VALOR("^[1-9]+[.,]?[0-9]{1,2}$") // XXX+.XX - número em ponto flutuante com no máximo duas casas decimais.*/
	VALOR("^[0-9]+[.,]?[0-9]{1,2}$"), // XXX+.XX - número em ponto flutuante com no máximo duas casas decimais.
					
	/**TELEFONE_FIXO("[(]\\d{2}[)]\\d{4}-\\d{4}"), // (DD)DDDD-DDDD*/
	TELEFONE_FIXO("[(]\\d{2}[)]\\d{4}-\\d{4}"), // (DD)DDDD-DDDD
	
	/**TELEFONE_MOVEL("[(]\\d{2}[)]9\\d{4}-\\d{4}"), // (DD)9DDDD-DDDD*/
	TELEFONE_MOVEL("[(]\\d{2}[)]9\\d{4}-\\d{4}"), // (DD)9DDDD-DDDD
	
	ANO("[0-9]{4}"),
	
	MARCA("\\w+(\\s*\\w+)*"),
	
	MODELO("\\w+(\\s*\\w+)*"),
	
	PECA("[A-Z,a-z,0-9ÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇáàâãéèêíïóôõöúç]+(\\s[A-Z,a-z,0-9ÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇáàâãéèêíïóôõöúç]+)*"),
	
	TEXTO_E_NUMEROS("[A-Z,a-z,0-9ÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇáàâãéèêíïóôõöúç]+(\\s[A-Z,a-z,0-9ÁÀÂÃÉÈÊÍÏÓÔÕÖÚÇáàâãéèêíïóôõöúç]+)*"),
	
	QUANTIDADE("[0-9]+"),
	
	CARTAO("[0-9]{14,16}"),
	
	STATUS("(?i)CrIada|(Aprovada)|(concluida)|(PAGA)"),	
	
	/**PLACA("[A-Z][a-z]{3}-\\d{4}"), // LLL-NNNN - onde L são letras e N são números*/
	PLACA("[A-Z,a-z]{3}-[0-9]{4}"); // LLL-NNNN - onde L são letras e N são números
		
	private String mascara;

	private Mascaras(String mascara) {
		this.mascara = mascara;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
	}	
}// enum Mascaras
