package br.com.webservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.interfacebean.IAutentica;
import br.com.modelo.Usuario;
 
@Stateless
@LocalBean
@Path("/hellows/{usuario}/{senha}")
public class UsuarioWebService {	
    @EJB
    private IAutentica iautentica;
    
    @GET
    @Produces("text/plain")
    public String sayHello(@PathParam("usuario") String user, @PathParam("senha") String pass) {
    	Usuario usuario = new Usuario(user, pass);
    	usuario = iautentica.autentica(usuario);
    	if(usuario != null)
    		return usuario.toString();
    	else
    		return "Falha na Autenticação.";
    }
}