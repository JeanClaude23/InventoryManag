/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import DAO.GenericDao;
import Model.Sale;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

/**
 * REST Web Service
 *
 * @author JClaude
 */
@Path("sales")
public class SalesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SalesResource
     */
    public SalesResource() {
    }
    
    String DB = "jdbc:mysql://192.168....:3306/stockintern";
    GenericDao<Sale> dao = new GenericDao<Sale>(Sale.class);
    
    Sale sale = new Sale();
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveResource(@FormParam("quantity") int squantity) {
            
             return Response.status(Response.Status.CREATED).build();
             //System.out.println("Product Saved Successfully!!");
    }
    @GET
    @Path("Display")
    @Produces(MediaType.APPLICATION_JSON)
    public  List<Sale> saleList()  {
          List<Sale> saleList = new ArrayList();
          try{
          
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn = DriverManager.getConnection(DB, "root", "");
           Statement st = conn.createStatement();
           ResultSet rs = st.executeQuery("select * from Sale");
           
           while (rs.next()){
           Sale s = new Sale(rs.getInt("sale_id"),rs.getDate("sale_date"));
           saleList.add(s);
           }
           rs.close();
          }catch(Exception e){
              System.out.println("error occured"+ e);
          }
     return saleList;
    }

    /**
     * PUT method for updating or creating an instance of SalesResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
