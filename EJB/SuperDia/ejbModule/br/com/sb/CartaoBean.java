package br.com.sb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.interfacebean.ICartao;
import br.com.modelo.Cartao;
import net.webservicex.CCChecker;
import net.webservicex.CCCheckerSoap;

@Stateless
@Remote(ICartao.class)
public class CartaoBean implements ICartao {
	@Override
	public String validateCardNumber(String cardType, String cardNumber) {
		try {
		CCChecker ccChecker = new CCChecker();
		CCCheckerSoap ccCheckerSoap = ccChecker.getCCCheckerSoap();
		
		return ccCheckerSoap.validateCardNumber(cardType, cardNumber);
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<String> getCartoes() {
		List<String> cartoes = new ArrayList<>();
		for(Cartao.bandeiras cartao : Cartao.bandeiras.values())
			cartoes.add(cartao.name());
		return cartoes;
	}
	
}
