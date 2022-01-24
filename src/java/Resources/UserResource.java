/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import DAO.GenericDao;
import Model.Account;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author JClaude
 */
@Path("user")
public class UserResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UserResource
     */
    public UserResource() {
    }
    GenericDao gdao = new GenericDao(Account.class);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Account saveUsers(@FormParam("name") String uname, @FormParam("email") String mail, 
            @FormParam("password") String pass) {

        Account acc = new Account();
        acc.setName(uname);
        acc.setEmail(mail);
        acc.setPassword(pass);

        gdao.save(acc);
        return acc;
    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getUsers() {
        return gdao.findAll();
    }

    /**
     * PUT method for updating or creating an instance of UserResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
