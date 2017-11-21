package br.com.ws;

// Que porra é essa aqui mano!
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@WebService
@Stateless
@LocalBean
public class Cotacao {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String DollarToReal() {
		return "R$ 3.40";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getEuroToReal() {
		return "R$ 4.10";
	}
}
