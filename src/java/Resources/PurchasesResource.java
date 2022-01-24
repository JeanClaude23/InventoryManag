/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import DAO.GenericDao;
import Model.Product;
import Model.Purchase;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author JClaude
 */
@Path("purch")
public class PurchasesResource {

    public PurchasesResource() {
    }
    String DB = "jdbc:mysql://localhost:3306/stockintern";
    GenericDao<Purchase> pdao = new GenericDao<Purchase>(Purchase.class);
    
    @POST
    @Path("makepurchase")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveResource(@FormParam("quantity") int purchasequant, 
            @FormParam("purchaseUnitCost") int purchaseunitcos)  {
        
        return Response.status(Response.Status.CREATED).build();
        
    }
    
    @GET
    @Path("Display")
    @Produces(MediaType.APPLICATION_JSON)
    public  List<Purchase> purchaseList()  {
          List<Purchase> purchaseList = new ArrayList();
          try{
          
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn = DriverManager.getConnection(DB, "root", "");
           Statement st = conn.createStatement();
           ResultSet rs = st.executeQuery("select * from Purchase");
           
           while (rs.next()){
           Purchase p = new Purchase(rs.getInt("purchase_id"),rs.getDate("purchase_date"));
           purchaseList.add(p);
           }
           rs.close();
          }catch(Exception e){
              System.out.println("error occured"+ e);
          }
     return purchaseList;
    }
    
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Purchase> AllPurchases() {

        return pdao.findAll();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putJson(@PathParam("id") int id) {
        Purchase pur = pdao.findById(id);
        if (!Objects.isNull(pur)) {
            System.out.println(pur.getProducts());
            pdao.update(pur);
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteJson(@PathParam("id") int id) {
        Purchase pur = pdao.findById(id);
        pdao.delete(pur);
        return Response.status(Response.Status.OK).build();

    }
}
