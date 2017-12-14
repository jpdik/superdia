package br.com.webservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.swing.plaf.metal.MetalPopupMenuSeparatorUI;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.interfacebean.IAutentica;
import br.com.interfacebean.ICliente;
import br.com.modelo.Usuario;
 
/**
 * @author Deborah
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
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/logar")
    public String Logar(@FormParam("usuario") String user, @FormParam("senha") String pass) {
    	Usuario usuario = new Usuario(user, pass);
    	usuario = iautentica.autentica(usuario);
    	if(usuario != null)
    		return usuario.toString();
    	else
    		return MessagensJSON.ERRO_USUARIO_INVALIDO.getMensagem();
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/cadastrar")
    public String Cadastrar(@FormParam("usuario") String user, @FormParam("senha") String pass) {
    	Usuario usuario = new Usuario(user, pass);
    	if(icliente.autoAdiciona(usuario))
    		return MessagensJSON.USUARIO_CADASTRADO_SUCESSO.getMensagem();
    	else
    		return MessagensJSON.USUARIO_CADASTRADO_FALHA.getMensagem();
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/alterar")
    public String Alterar(@FormParam("usuarioAntigo") String oldUser, @FormParam("senhaAntiga") String oldPass,
    		@FormParam("novoUsuario") String newUser, @FormParam("novaSenha") String newPass) {
    	Usuario usuario = new Usuario(oldUser, oldPass);
    	usuario = iautentica.autentica(usuario);
    	if(usuario != null)
    		if(icliente.autoAltera(new Usuario(newUser, newPass)))
    			return MessagensJSON.INFORMACAO_ALTERADA_SUCESSO.getMensagem();
    		else
    			return MessagensJSON.USUARIO_INALTERADO.getMensagem();
    	else
    		return MessagensJSON.ALTERAR_USUARIO_FALHA.getMensagem();
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/remover")
    public String Remover(@FormParam("usuario")String user, @FormParam("senha")String senha){
    	Usuario usuario = new Usuario(user,senha);
    	usuario = iautentica.autentica(usuario);
    	if(usuario != null)
    		if(icliente.autoRemove(usuario))
    			return MessagensJSON.USUARIO_REMOVIDO_SUCESSO.getMensagem();
    		else
    			return MessagensJSON.USUARIO_NAO_EXISTE.getMensagem();
    	else
    		return MessagensJSON.ALTERAR_USUARIO_FALHA.getMensagem();
    }
}