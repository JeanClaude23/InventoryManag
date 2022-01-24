/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Db;

import Model.Account;
import Model.Category;
import Model.Product;
import Model.Purchase;
import Model.Sale;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author JClaude
 */
public class MainClass {
    public static void main(String[] args) {
        Account account = new Account();
        
        Category category1 = new Category();
        Category category2 = new Category();
        
        Product product1 = new Product();
        Product product2 = new Product();
        
        Purchase purchase = new Purchase();
        
        Sale sale = new Sale();
        
        account.setName("Jean Claude");
        account.setEmail("mjeanclaude23@gmail.com");
        account.setPassword("123");
        
        category1.setName("Shoes");
        category2.setName("T-Shirt");
        
        product1.setName("Jordans");
        product1.setCategory(category1);
        product1.setUnit_price(15000);
        
        product2.setName("LaCoste");
        product2.setCategory(category2);
        product2.setUnit_price(8000);
        
        purchase.setDate(new Date());
        product1.setQuantity(3);
        product1.setUnit_price(10000);
        
        int total_cost = product1.getQuantity() * product1.getUnit_cost();
        
        purchase.setTotal_cost(total_cost);
        purchase.getProducts().add(product1);
        
        sale.setDate(new Date());
        int q=2;
        int total_price = product1.getUnit_price()*q;
        int quantity = product1.getQuantity() - q;
        product1.setQuantity(quantity);
        sale.setTotal_price(total_price);
        sale.getProducts().add(product1);
        
        SessionFactory sessFact = new Configuration().configure().addAnnotatedClass(Category.class)
                .addAnnotatedClass(Purchase.class).addAnnotatedClass(Product.class)
                .addAnnotatedClass(Sale.class).addAnnotatedClass(Account.class).buildSessionFactory();
        Session sess = sessFact.getCurrentSession();
        sess.beginTransaction();
        sess.save(account);
        sess.save(category1);
        sess.save(category2);
        sess.save(product1);
        sess.save(product2);
        sess.save(purchase);
        sess.save(sale);
        sess.getTransaction().commit();
    }
}
