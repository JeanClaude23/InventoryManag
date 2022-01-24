/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.GenericDao;
import Model.Category;
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
@WebServlet(name = "CategoryEdit", urlPatterns = {"/CategoryEdit"})
public class CategoryEdit extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CategoryEdit() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pw = response.getWriter();

        String name = request.getParameter("categoryName");
        int id = Integer.parseInt(request.getParameter("categoryId"));

        Category category = new Category();

        List<Category> categories = new ArrayList<Category>();

        GenericDao<Category> cdao = new GenericDao<Category>(Category.class);

        Query query = cdao.createSession().createQuery("FROM Category c WHERE c.name = '" + name + "'");

        categories = query.list();

        if (categories.size() > 0) {
            pw.println("Category Already exist, Please try another Name");
        } else {

            category = cdao.findById(id);

            category.setName(name);

            try {
                cdao.update(category);
                pw.println("Category Updated");

            } catch (Exception e) {
                pw.println("Edit Error");
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
