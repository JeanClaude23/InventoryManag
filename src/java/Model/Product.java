/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author JClaude
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private int id;
    @Column(name = "product_name")
    private String name;
    @Column(name = "product_quantity", columnDefinition = "int default 0")
    private int quantity;
    @Column(name = "product_unit_price")
    private int unit_price;
    @Column(name = "product_unit_cost", columnDefinition = "int default 0")
    private int unit_cost;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, mappedBy = "products")
    private List<Purchase> purchases =  new ArrayList<Purchase>();
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, mappedBy = "products")
    private List<Sale> sales = new ArrayList<Sale>();

    public Product() {
        super();
    }
    public Product(String name, int quantity, int unit_price, int unit_cost) {
        this.name = name;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.unit_cost = unit_cost;
        
    }
    public Product(String name, int quantity, int unit_price, int unit_cost, Category category,
            List<Purchase> purchases, List<Sale> sales) {
        super();
        this.name = name;
        this.quantity = quantity;
        this.unit_price = unit_price;
        this.unit_cost = unit_cost;
        this.category = category;
        this.purchases = purchases;
        this.sales = sales;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }

    public int getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(int unit_cost) {
        this.unit_cost = unit_cost;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", quantity=" + quantity + ", unit_price=" + unit_price + ", unit_cost=" + unit_cost + ", category=" + category + ", purchases=" + purchases + ", sales=" + sales + '}';
    }
    
    
    
    
    
}
