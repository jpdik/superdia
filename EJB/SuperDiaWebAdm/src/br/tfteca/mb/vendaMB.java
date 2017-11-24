package br.tfteca.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.interfacebean.IVenda;
import br.com.modelo.Venda;

@ManagedBean
@SessionScoped
public class vendaMB {
	
	@EJB
	private IVenda iVenda;
	
	public void remove(Venda venda) {
		iVenda.remove(venda);
	}
	
	public List<Venda> getVendas() {
		return iVenda.listaTodos();
	}
}
