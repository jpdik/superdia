package br.com.interfacebean;

import java.util.List;

public interface ICartao {
	String validateCardNumber(String cardType, String cardNumber);
	List<String> getCartoes();
}
