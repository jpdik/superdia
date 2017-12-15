package br.com.interfacebean;

public interface ICartao {
	/**
	 * Valida o número do cartão inserido.
	 * 
	 * @param cardNumber - Número do cartão que será analisado
	 * 
	 * @return <b>True</b> se foi válido ou <b>False</b> caso contrário.
	*/
	boolean validaNumeroCartao(String cardNumber);
	
	/**
	 * Obtém a bandeira do cartão
	 * 
	 * @param cardNumber - Número do cartão que será analisado
	 * 
	 * @return <b>String</b> com a bandeira do cartão se for válido, caso contrário retorna uma <b>String 'NONE'</b>
	*/
	String getBandeira(String cardNumber);
}
