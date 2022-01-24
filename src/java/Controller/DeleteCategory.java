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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;

/**
 *
 * @author JClaude
 */
@WebServlet(name = "DeleteCategory", urlPatterns = {"/DeleteCategory"})
public class DeleteCategory extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public DeleteCategory() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pw = response.getWriter();
        int id = Integer.parseInt(request.getParameter("categoryId"));

        Category category = new Category();
        GenericDao<Category> cdao = new GenericDao<Category>(Category.class);

        List<Product> products = new ArrayList<Product>();
        GenericDao<Product> pdao = new GenericDao<Product>(Product.class);

        Query query = pdao.createSession().createQuery("FROM Product p WHERE p.category = '" + id + "'");
        products = query.list();

        if (products.size() > 0) {
            pw.println("Category can not be deleted have products depending on it");
        } else {
            category = cdao.findById(id);

            try {
                cdao.delete(category);
                pw.println("Category Deleted Successfully!!");

            } catch (Exception e) {
                pw.println("Delete Error");
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
