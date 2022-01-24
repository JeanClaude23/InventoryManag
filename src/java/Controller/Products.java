/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.GenericDao;
import Model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@WebServlet(name = "Products", urlPatterns = {"/Products"})
public class Products extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Products() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pw = response.getWriter();

        ObjectMapper obmap = new ObjectMapper();
        int catid = Integer.parseInt(request.getParameter("categoryId"));
        List<Product> list = new ArrayList<Product>();
        GenericDao<Product> gdao = new GenericDao<Product>(Product.class);

        Query query = gdao.createSession().createQuery("From Product P WHERE P.category = '" + catid + "'");
        list = query.list();

        String jsonString = obmap.writeValueAsString(list);
        pw.println(jsonString);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
