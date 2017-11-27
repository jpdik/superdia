package br.com.webservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.interfacebean.IAutentica;
import br.com.interfacebean.ICliente;
import br.com.modelo.Usuario;
 
/**
 * @author Joao_Paulo
 *
 *	ATENÇÃO: Ao realizarem alterações aqui, o servidor glassfish precisa ter seus projetos
 *	limpos (dar um clean em (Project>Clean...) e reiniciado pelo restart. Senão o WebService
 *	encontrará somente nullpointer nas classes EJB. Talves exista um fix, mas quando não tiver vamos
 *	trabalhar desta forma.
 */

@Stateless
@LocalBean
@Path("/usuario")
public class UsuarioWebService {	
    @EJB
    private IAutentica iautentica;
    
    @EJB
    private ICliente icliente;
    
    @GET
    @Produces("text/plain")
    @Path("/logar/{usuario}/{senha}")
    public String Logar(@PathParam("usuario") String user, @PathParam("senha") String pass) {
    	Usuario usuario = new Usuario(user, pass);
    	usuario = iautentica.autentica(usuario);
    	if(usuario != null)
    		return usuario.toString();
    	else
    		return "Falha na Autenticação.";
    }
    
    @GET
    @Produces("text/plain")
    @Path("/cadastrar/{usuario}/{senha}")
    public String Cadastrar(@PathParam("usuario") String user, @PathParam("senha") String pass) {
    	Usuario usuario = new Usuario(user, pass);
    	if(icliente.autoAdiciona(usuario))
    		return "Cadastrado com sucesso!";
    	else
    		return "Falha no Cadastro(usuário já existente).";
    }
    
    @GET
    @Produces("text/plain")
    @Path("/alterar/{usuarioAntigo}/{senhaAntiga}/{novoUsuario}/{novaSenha}")
    public String Alterar(@PathParam("usuarioAntigo") String oldUser, @PathParam("senhaAntiga") String oldPass,
    		@PathParam("novoUsuario") String newUser, @PathParam("novaSenha") String newPass) {
    	Usuario usuario = new Usuario(oldUser, oldPass);
    	usuario = iautentica.autentica(usuario);
    	if(usuario != null)
    		if(icliente.autoAltera(new Usuario(newUser, newPass)))
    			return "Informações alteradas com sucesso!";
    		else
    			return "Usuário já existente. (Inalterado)";
    	else
    		return "Usuário que deseja alterar não existe, ou informações não coincidem.";
    }
}