/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import DAO.GenericDao;
import Model.Category;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author JClaude
 */
@Path("cate")
public class CategoryResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CategoryResource
     */
    String DB = "jdbc:mysql://192.168....:3306/stockintern";
    public CategoryResource() {
    }

    GenericDao<Category> gdao = new GenericDao(Category.class);

    @POST
    @Path("category")
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveResource(@FormParam("categoryname") String cname) {

        Category cat = new Category();
        cat.setName(cname);
        gdao.save(cat);
        return Response.status(Response.Status.CREATED).build();

    }
//    @POST
//    @Path("category")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response save(Category category) {
//        gdao.save(category);
//        return Response.status(Response.Status.CREATED).build();
//    }

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> AllCategories() {

        return gdao.findAll();
    }
    
    @POST
    @Path("update")
//    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_HTML)
    public Response updateCategory(@FormParam("id") String id,@FormParam("name") String name){
        try{
          Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection(DB,"root","");
          
          PreparedStatement pst = conn.prepareStatement("UPDATE Category SET category_name ='"+name+"' WHERE category_id ='"+id+"'" );
          pst.executeUpdate();
           
          URI uri2 = new URI("http://192.168....:8080//InventoryManagementSystem/categoryForm.jsp");
          
          return Response.temporaryRedirect(uri2).build();
      }catch(Exception e){
          String output ="error";
          return Response.status(200).entity(output).build();
      }
    }
    
    @GET
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@QueryParam("id")String id){
        String foreignKey = "";
       try{
          Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection(DB,"root","");
           PreparedStatement pst = conn.prepareStatement("UPDATE PRODUCT SET category_id=0 where category_id = '"+id+"'" );
          pst.executeUpdate();
          
          pst = conn.prepareStatement("DELETE FROM Category where id = '"+id+"'" );
          pst.executeUpdate();

          URI uri2 = new URI("http://192.168....:8080//InventoryManagementSystem/categoryForm.jsp");          
          return Response.temporaryRedirect(uri2).build();
       }catch(Exception e){
          String output ="error "+e;
          return Response.status(200).entity(output).build();
       }
    }
    
    
    
//    @PUT
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response putJson(@PathParam("id") int id) {
//        Category cat = gdao.findById(id);
//        if (!Objects.isNull(cat)) {
//            System.out.println(cat.getName());
//            gdao.update(cat);
//            return Response.status(Response.Status.OK).build();
//        } else {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @DELETE
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/{id}")
//    public Response deleteJson(@PathParam("id") int id) {
//        Category cat = gdao.findById(id);
//        gdao.delete(cat);
//        return Response.status(Response.Status.OK).build();
//
//    }
}
