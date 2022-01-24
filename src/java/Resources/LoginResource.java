/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import DAO.GenericDao;
import Model.Account;
import java.util.ArrayList;
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
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import javax.servlet.http.HttpSession;

/**
 * REST Web Service
 *
 * @author JClaude
 */
@Path("users")
public class LoginResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LoginResource
     */
    GenericDao gdao = new GenericDao(Account.class);

    public LoginResource() {
    }

    @POST
    @Path("login")
    public Response authenticate(@FormParam("email") String email, @FormParam("password") String password) {
        String username="";
        Account acc = new Account();
        String DB = "jdbc:mysql://192.168....:3306/stockintern";
        int counter = 0;
        List<Account> UserList = new ArrayList<>();
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB, "root", "");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from Account where register_email='" + email + "' and register_password='" + password + "'");

            while (rs.next()) {
                counter = counter + 1;
                    username=rs.getString("register_name");
          //      UserList.add(u);
            }
            rs.close();
            String whitespace = " ";
            String no_whitespace = "%20";
            String full_username = username.replace(whitespace, no_whitespace);
            if (counter >= 1) {
                URI url = new URI("http://192.168...:8080/InventoryManagementSystem/category.jsp?user=" + full_username);
                return Response.temporaryRedirect(url).build();
//            String output = "we found in if"+counter;
//            return Response.status(200).entity(output).build();
            } else {
                String message = "There%20was%20an%20error,%20Please%20try%20again";
                URI url = new URI("http://192.168....:8080/InventoryManagementSystem/index.jsp?message=" + message);
                return Response.temporaryRedirect(url).build();
//            String output = "we found in else "+counter;
//            return Response.status(200).entity(output).build();
            }
        } catch (Exception ex) {

            String output = "an error occured"+ex;
            return Response.status(200).entity(output).build();

        }
    }

//    @POST
//    @Path("login/{name}/{password}")
//    @Consumes (MediaType.APPLICATION_JSON)
//    public Response login(@PathParam("name") String name,@PathParam("password") String password){
//        UserDao.loginByUsernameAndPassword(name,password);
//        return Response.status(Response.Status.OK).build();
//    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> login() {

        return gdao.findAll();
    }

    /**
     * PUT method for updating or creating an instance of LoginResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
