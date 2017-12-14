package br.com.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1549257097189053846L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="Defina um nome para o produto!")
	private String nome;
	private String descricao;
	
	@Min(value=0, message="O preço do produto deve ser válido!")
	@NotNull(message="Preço do produto não pode ser vazio!")
	private Double preco;
	
	@Min(value=0, message="Estoque mínimo não de ver negativo!")
	private Integer estoqueMinimo;
	
	@Min(value=0, message="Quantidade do produto em estoque não de ver negativa!")
	@NotNull(message="Necessário informar a quantidade em estoque!")
	private Integer quantidadeEstoque;
	
	private String imagem;
	
	private String vendidoPor;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Double getPreco() {
		return preco;
	}
	
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public Integer getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Integer quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getVendidoPor() {
		return vendidoPor;
	}

	public void setVendidoPor(String vendidoPor) {
		this.vendidoPor = vendidoPor;
	}

	@Override
	public String toString() {
		return String.format(
				"Nome: %s, descrição: %s, preço: %l,estoque mínimo: %d,"
				+ "quantidade em estoque: %d", nome, descricao, preco, 
				estoqueMinimo, quantidadeEstoque);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
