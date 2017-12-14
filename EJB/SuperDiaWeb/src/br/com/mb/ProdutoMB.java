package br.com.mb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import br.com.interfacebean.IProduto;
import br.com.modelo.Produto;

@ManagedBean
@ViewScoped
public class ProdutoMB {
	Produto produto = new Produto();
	
	String filtro = new String();
	
	@EJB
	private IProduto iProduto;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutosInternos() {
		List<Produto> produtos = new ArrayList<>();
		List<Produto> lista = iProduto.listaTodos();
		if(filtro.isEmpty())
			return lista;
		
		for(Produto p : lista)
			if(p.getNome().contains(filtro))
				produtos.add(p);
		return produtos;
	}
	
	public List<Produto> getProdutosExternos() {
		List<Produto> produtos = new ArrayList<>();

		/*try {
			String url = "https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
			String response = getHTML(url);
			JSONObject object = new JSONObject(response);
			JSONArray ob = object.getJSONArray("products");

			for (int i = 0; i < ob.length(); i++) {
				Produto p = new Produto();

				p.setVendidoPor(ob.getJSONObject(i).get("vendor").toString());
				p.setNome(ob.getJSONObject(i).get("title").toString());
				p.setDescricao(ob.getJSONObject(i).get("tags").toString());
				p.setImagem(ob.getJSONObject(i).getJSONObject("image").get("src").toString());
				
				JSONObject td = ob.getJSONObject(i).getJSONArray("variants").getJSONObject(0);
				p.setQuantidadeEstoque(Integer.valueOf(td.get("inventory_quantity").toString()));
				p.setEstoqueMinimo(Integer.valueOf(td.get("old_inventory_quantity").toString()));
				p.setPreco(Double.valueOf(td.get("price").toString()));
				
				if(filtro.isEmpty())
					produtos.add(p);
				else if(p.getNome().contains(filtro)) {
					produtos.add(p);
				}
			}

			return produtos;
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return null;
	}

	private static String getHTML(String urlToRead) throws Exception {
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(urlToRead);

		Response response = target.request().get();

		response.bufferEntity();

		return response.readEntity(String.class);
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	public String busca() {
		return "menu?faces-redirect=true";
	}
	
	public String limpaBusca() {
		filtro = new String();
		return "menu?faces-redirect=true";
	}
	
}
