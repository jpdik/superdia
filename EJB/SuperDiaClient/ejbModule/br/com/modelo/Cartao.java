package br.com.modelo;

import java.io.Serializable;

public class Cartao implements Serializable{
	
	private static final long serialVersionUID = -6734282485557717695L;
	
	private String numero;
	private String bandeira;
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	} 
}
