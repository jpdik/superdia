package br.com.webservices;



import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.interfacebean.IProduto;
import br.com.modelo.Produto;

@Stateless
@LocalBean
@Path("/Produto")
public class ProdutoWebService {
	@EJB
	private IProduto iProduto;
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/adiciona")
	public String Adicionar(@FormParam("nome") String nome, @FormParam("descricao") String descricao, @FormParam("preco") Double preco,
			@FormParam("estoqueMinimo") Integer estoqueMinimo, @FormParam("qtde") Integer qtde) {
		Produto produto = new Produto(nome, descricao, preco, estoqueMinimo, qtde);
		if(iProduto.adiciona(produto))
			return MessagensJSON.PRODUTO_CADASTRADO_SUCESSO.getMensagem();
		else
			return MessagensJSON.PRODUTO_CADASTRADO_FALHA.getMensagem();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/altera")
	public String Altera(@FormParam("nome") String nome, @FormParam("descricao") String descricao, @FormParam("preco") Double preco,
			@FormParam("estoqueMinimo") Integer estoqueMinimo, @FormParam("qtde") Integer qtde) {
		Produto produto = new Produto(nome, descricao, preco, estoqueMinimo, qtde);
		if(iProduto.altera(produto))
			return MessagensJSON.ALTERAR_PRODUTO_SUCESSO.getMensagem();
		else
			return MessagensJSON.ALTERAR_PRODUTO_FALHA.getMensagem();
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/remove")
	public String remove(@FormParam("nome") String nome, @FormParam("descricao") String descricao, @FormParam("preco") Double preco,
			@FormParam("estoqueMinimo") Integer estoqueMinimo, @FormParam("qtde") Integer qtde) {
		Produto produto = new Produto(nome, descricao, preco, estoqueMinimo, qtde);
		if(iProduto.remove(produto))
			return MessagensJSON.PRODUTO_REMOVIDO_SUCESSO.getMensagem();
		else
			return MessagensJSON.PRODUTO_NAO_EXISTE.getMensagem();
	}
}
