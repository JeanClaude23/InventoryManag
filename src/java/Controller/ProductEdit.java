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
@WebServlet(name = "ProductEdit", urlPatterns = {"/ProductEdit"})
public class ProductEdit extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ProductEdit() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pw = response.getWriter();
        int id = Integer.parseInt(request.getParameter("productId"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));

        Product product = new Product();
        GenericDao<Product> pdao = new GenericDao<Product>(Product.class);

        product = pdao.findById(id);
        if (product == null) {
            pw.println("Product not Fund??");
        } else {
            product.setName(name);
            product.setUnit_price(price);
            try {
                pdao.update(product);
                pw.println("Product Updated Successfully!!");
            } catch (Exception e) {
                pw.println("Modification Error");
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
