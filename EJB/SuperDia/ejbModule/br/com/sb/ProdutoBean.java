package br.com.sb;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import br.com.interfacebean.IProduto;
import br.com.modelo.Produto;

@Stateless
@Remote(IProduto.class)
public class ProdutoBean implements IProduto {

	@PersistenceContext(name = "superdia")
	EntityManager em;

	@Override
	public boolean adiciona(Produto produto) {
		try {
			em.persist(produto);
			return true;
		} catch (EntityExistsException | IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public boolean altera(Produto produto) {
		try {
			em.merge(produto);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}

	}

	@Override
	public boolean remove(Produto produto) {
		try {
			em.remove(em.merge(produto));
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}

	}

	@Override
	public List<Produto> listaTodos() {
		CriteriaQuery<Produto> produtoQuery = em.getCriteriaBuilder().createQuery(Produto.class);

		produtoQuery.select(produtoQuery.from(Produto.class));

		return em.createQuery(produtoQuery).getResultList();
	}

	@Override
	public List<Produto> listaTodosExternos(String filtro) {
		List<Produto> produtos = new ArrayList<>();

		try {
			String url = "https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
			String response = getHTML(url);
			
			JsonReader jsonReader = Json.createReader(new StringReader(response));
			JsonObject object = jsonReader.readObject();
			JsonArray ob = object.getJsonArray("products");

			for (int i = 0; i < ob.size(); i++) {
				Produto p = new Produto();
				
				p.setId(Long.valueOf(ob.getJsonObject(i).get("id").toString().replace("\"", "")));
				p.setVendidoPor(ob.getJsonObject(i).get("vendor").toString().replace("\"", ""));
				p.setNome(ob.getJsonObject(i).get("title").toString().replace("\"", ""));
				p.setDescricao(ob.getJsonObject(i).get("tags").toString().replace("\"", ""));
				p.setImagem(ob.getJsonObject(i).getJsonObject("image").get("src").toString().replace("\"", ""));

				JsonObject td = ob.getJsonObject(i).getJsonArray("variants").getJsonObject(0);
				p.setQuantidadeEstoque(Integer.valueOf(td.get("inventory_quantity").toString().replace("\"", "")));
				p.setEstoqueMinimo(Integer.valueOf(td.get("old_inventory_quantity").toString().replace("\"", "")));
				p.setPreco(Double.valueOf(td.get("price").toString().replace("\"", "")));

				if (filtro.isEmpty())
					produtos.add(p);
				else if (p.getNome().contains(filtro)) {
					produtos.add(p);
				}
			}

			return produtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String getHTML(String urlToRead) throws Exception {
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(urlToRead);

		Response response = target.request().get();

		response.bufferEntity();

		return response.readEntity(String.class);
	}

	@Override
	public Produto buscaPorID(Long id) {
		return em.find(Produto.class, id);
	}
}
