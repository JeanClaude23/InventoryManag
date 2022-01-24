/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import DAO.GenericDao;
import Model.Category;
import Model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author JClaude
 */
@Path("res")
public class productresources {
    
     public productresources() {
    }
     String DB = "jdbc:mysql://192.168....:3306/stockintern";
    GenericDao<Category> catdao = new GenericDao<Category>(Category.class);
    GenericDao<Product> dao = new GenericDao<Product>(Product.class);

    Product p = new Product();
    Category cat = new Category();
    

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveResource(@FormParam("productname") String pname,
            @FormParam("productunitprice") int punitprice) {
            
            
             p.setName(pname);
             p.setUnit_price(punitprice);
             dao.save(p);
             return Response.status(Response.Status.CREATED).build();
             //System.out.println("Product Saved Successfully!!");
    }

    @GET
    @Path("Display")
    @Produces(MediaType.APPLICATION_JSON)
    public  List<Product> productList()  {
          List<Product> productList = new ArrayList();
          try{
          
           Class.forName("com.mysql.jdbc.Driver");
           Connection conn = DriverManager.getConnection(DB, "root", "");
           Statement st = conn.createStatement();
           ResultSet rs = st.executeQuery("select * from Product");
           
           while (rs.next()){
           Product p = new Product(rs.getString("product_name"),rs.getInt("product_quantity"),
                   rs.getInt("product_unit_price"),rs.getInt("product_unit_cost"));
           productList.add(p);
           }
           rs.close();
          }catch(Exception e){
              System.out.println("error occured"+ e);
          }
     return productList;
    }
}
