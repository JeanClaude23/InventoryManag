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
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JClaude
 */
@WebServlet(name = "AddCategory", urlPatterns = {"/AddCategory"})
public class AddCategory extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddCategory() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw = response.getWriter();

        int i = 0;
        List<Category> list = new ArrayList<Category>();
        Category ct = new Category();

        GenericDao<Category> gdao = new GenericDao<Category>(Category.class);

        String category = request.getParameter("category");

        if (category == "") {
            pw.println("Category field is empty!!");
        } else {
            list = gdao.findAll();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                Category allCategories = (Category) iterator.next();
                if (allCategories.getName().equals(category)) {

                    i++;

                    pw.println("Category Exist");
                }
            }
            if (i == 0) {
                try {

                    ct.setName(category);
                    gdao.save(ct);

                    pw.println("Category Created Successfully!!");
                } catch (Exception e) {
                    pw.println(e);
                }
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
