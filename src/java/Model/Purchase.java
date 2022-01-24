/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JClaude
 */
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "purchase_id")
    private int id;
    @Column(name = "purchase_total_cost")
    private int total_cost;
    @Temporal(TemporalType.DATE)
    @Column(name = "purchase_date")
    private Date date;
    
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "purchase_product",joinColumns = {@JoinColumn(name = "purchase_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    @JsonIgnore
    private List<Product> products = new ArrayList<Product>();

    public Purchase() {
    }
    public Purchase(int id, Date date) {
        this.id = id;
        this.date = date;
    }
    public Purchase(int total_cost, Date date, List<Product> products) {
        super();
        this.total_cost = total_cost;
        this.date = date;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    
    
}
