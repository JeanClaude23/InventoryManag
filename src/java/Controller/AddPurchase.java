/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.GenericDao;
import Model.Category;
import Model.Product;
import Model.Purchase;
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
@WebServlet(name = "AddPurchase", urlPatterns = {"/AddPurchase"})
public class AddPurchase extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AddPurchase() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter pw = response.getWriter();
        ObjectMapper obmap = new ObjectMapper();

        String myJsonData = request.getParameter("purchaseData");

        ArrayList<Product> products = obmap.readValue(myJsonData,
                new TypeReference<ArrayList<Product>>() {
        });

        Purchase purchase = new Purchase();
        Product prod = new Product();

        GenericDao<Product> prodao = new GenericDao<Product>(Product.class);
        GenericDao<Purchase> pdao = new GenericDao<Purchase>(Purchase.class);

        int productId = 0;
        int totalCost = 0;
        int quantity = 0;
        purchase.setDate(new Date());

        for (Iterator iterator = products.iterator(); iterator.hasNext();) {

            Product product = (Product) iterator.next();
            productId = product.getId();

            prod = prodao.findById(productId);
            quantity = product.getQuantity() + prod.getQuantity();

            String name = prod.getName();
            Category category = prod.getCategory();
            int price = prod.getUnit_price();

            product.setName(name);
            product.setCategory(category);
            product.setUnit_price(price);

            purchase.getProducts().add(product);
            totalCost += product.getUnit_cost() * product.getQuantity();
            product.setQuantity(quantity);

            purchase.setTotal_cost(totalCost);

            try {
                pdao.save(purchase);
                pw.println("Purchase Saved Successfully!!");
            } catch (Exception e) {
                pw.println(e);
            }

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
