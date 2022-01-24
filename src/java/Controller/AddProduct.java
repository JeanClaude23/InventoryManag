/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.GenericDao;
import Model.Category;
import Model.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JClaude
 */
@WebServlet(name = "AddProduct", urlPatterns = {"/AddProduct"})
public class AddProduct extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddProduct() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pw = response.getWriter();

        Product product = new Product();
        Category cat = new Category();
        GenericDao<Category> catdao = new GenericDao<Category>(Category.class);
        GenericDao<Product> dao = new GenericDao<Product>(Product.class);

        String name = request.getParameter("name");
        int category = Integer.parseInt(request.getParameter("category"));
        int price = Integer.parseInt(request.getParameter("price"));

        if (name == "" || category == 0 || price == 0) {
            pw.println("All Fields are Required!!");
        } else {
            try {
                
                cat = catdao.findById(category);
                product.setName(name);
                product.setUnit_price(price);
                product.setCategory(cat);

                dao.save(product);

                pw.println("Product Saved Successfully!!");

            } catch (Exception e) {
                pw.println(e);
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
