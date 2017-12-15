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
			LuhnChecker luhnChecker = new LuhnChecker();
			LuhnCheckerSoap luhnCheckerSoap = luhnChecker.getLuhnCheckerSoap();
			return luhnCheckerSoap.checkCC(cardNumber).getCardType();
		} catch (Exception e) {
			return null;
		}
	}

}
