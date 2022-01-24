/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.GenericDao;
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
@WebServlet(name = "DeleteProduct", urlPatterns = {"/DeleteProduct"})
public class DeleteProduct extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DeleteProduct() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pw = response.getWriter();
        int id = Integer.parseInt(request.getParameter("productId"));

        Product product = new Product();
        GenericDao<Product> pdao = new GenericDao<Product>(Product.class);

        product = pdao.findById(id);
        int quantity = product.getQuantity();
        if (quantity > 0) {
            pw.println("Product can not be Deleted, have some quantity");
        } else {
            try {
                pdao.delete(product);
                pw.println("Product Deleted Successfully!!");
            } catch (Exception e) {
                pw.println("Delete Error");
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
