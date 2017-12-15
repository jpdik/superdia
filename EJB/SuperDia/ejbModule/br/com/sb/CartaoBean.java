package br.com.sb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.cdyne.ws.LuhnChecker;
import com.cdyne.ws.LuhnCheckerSoap;

import br.com.interfacebean.ICartao;

@Stateless
@Remote(ICartao.class)
public class CartaoBean implements ICartao {
	@Override
	public boolean validaNumeroCartao(String cardNumber) {
		try {
			System.setProperty("http.proxyHost", "10.0.0.254");
			System.setProperty("http.proxyPort", "8080");
			System.setProperty("https.proxyHost", "10.0.0.254");
			System.setProperty("https.proxyPort", "8080");
			
			//https://ws.cdyne.com/creditcardverify/luhnchecker.asmx?WSDL
			LuhnChecker luhnChecker = new LuhnChecker();
			LuhnCheckerSoap luhnCheckerSoap = luhnChecker.getLuhnCheckerSoap();
			return luhnCheckerSoap.checkCC(cardNumber).isCardValid();
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String getBandeira(String cardNumber) {
		try {
			System.setProperty("http.proxyHost", "10.0.0.254");
			System.setProperty("http.proxyPort", "8080");
			System.setProperty("https.proxyHost", "10.0.0.254");
			System.setProperty("https.proxyPort", "8080");
			
			LuhnChecker luhnChecker = new LuhnChecker();
			LuhnCheckerSoap luhnCheckerSoap = luhnChecker.getLuhnCheckerSoap();
			return luhnCheckerSoap.checkCC(cardNumber).getCardType();
		} catch (Exception e) {
			return null;
		}
	}

}
