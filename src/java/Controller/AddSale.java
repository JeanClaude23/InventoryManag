/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.GenericDao;
import Model.Product;
import Model.Sale;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author JClaude
 */
@WebServlet(name = "AddSale", urlPatterns = {"/AddSale"})
public class AddSale extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddSale() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pw = response.getWriter();

        ObjectMapper obmap = new ObjectMapper();

        String myJsonData = request.getParameter("saleData");

        ArrayList<Product> products = obmap.readValue(myJsonData, new TypeReference<ArrayList<Product>>() {
        });

        Sale sale = new Sale();

        Product prod = new Product();

        GenericDao<Product> prodao = new GenericDao<Product>(Product.class);

        GenericDao<Sale> sdao = new GenericDao<Sale>(Sale.class);

        int totalPrice = 0;
        int size = products.size();
        int count = 0;

        for (Iterator iterator = products.iterator(); iterator.hasNext();) {
            Product product = (Product) iterator.next();

            int id = product.getId();

            int expectedQuantity = product.getQuantity();

            prod = prodao.findById(id);

            int actualQuantity = prod.getQuantity();

            int newQuantity = actualQuantity - expectedQuantity;

            totalPrice += expectedQuantity * prod.getUnit_price();

            if (newQuantity >= 0) {
                prod.setQuantity(newQuantity);
                sale.getProducts().add(prod);
                count++;
            }

        }
        sale.setDate(new Date());
        sale.setTotal_price(totalPrice);

        if (size == count) {

            try {
                sdao.save(sale);
                pw.println("Sale Saved Successfully!!");
            } catch (Exception e) {
                pw.println(e);
            }
        } else {
            pw.println("Some Products has invalid Quantity");
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
