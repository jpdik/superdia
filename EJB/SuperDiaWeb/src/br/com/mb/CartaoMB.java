package br.com.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.interfacebean.ICartao;
import br.com.modelo.Cartao;

@ManagedBean
@SessionScoped
public class CartaoMB {
	Cartao card = new Cartao();

	@EJB
	private ICartao iCartao;

	public boolean analisaCartao() {
		try {
			// System.out.println(iCartao.validateCardNumber(card.getBandeira(),
			// card.getNumero()));
		} catch (Exception e) {
			return true;
		}
		return true;
	}

	public Cartao getCard() {
		return card;
	}

	public void setCard(Cartao card) {
		this.card = card;
	}

	public List<String> getCartoes() {
		try {
			return iCartao.getCartoes();
		} catch (javax.ejb.TransactionRolledbackLocalException e) {
			return null;
		}
	}
}
