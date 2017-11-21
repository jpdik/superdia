package br.com.resources;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Stateless
@LocalBean
@Path("/usuario")
public class User {

    public User() {
        // TODO Auto-generated constructor stub
    }
 
    @GET
    @Produces("text/plain")
    @Path("/oi")
    public String digaOla() {
        return "Hello";
    }
}