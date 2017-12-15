package br.com.interfacebean;

public interface ICartao {
	boolean validateCardNumber(String cardNumber);
	String getBandeira(String cardNumber);
}
