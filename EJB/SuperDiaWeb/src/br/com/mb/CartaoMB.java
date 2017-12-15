package br.com.mb;

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
		return iCartao.validaNumeroCartao(card.getNumero());
	}

	public Cartao getCard() {
		return card;
	}

	public void setCard(Cartao card) {
		this.card = card;
	}
	
	public void numeroMudou() {
		card.setBandeira(iCartao.getBandeira(card.getNumero()));
		System.out.println(card.getBandeira());
	}

}
