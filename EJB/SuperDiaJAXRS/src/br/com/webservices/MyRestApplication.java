package br.com.webservices;

import java.util.HashSet;
import java.util.Set;
 
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
 

/** @author Joao_Paulo
 * 
 *	Esta classe inicializa a conexão do RESTFul com as classes do pacote.
 *  não modifique.
 */
@ApplicationPath("/rest")
public class MyRestApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(UsuarioWebService.class);
        return classes;
    }
}