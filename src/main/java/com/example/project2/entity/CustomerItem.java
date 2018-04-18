package com.example.project2.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customer_item")
public class CustomerItem implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CUSTOMER_ITEM_ID")
    private Long id;
    @Column
    private int qty;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;
    @ManyToMany
    @JoinTable(name = "customer_item_item",
            joinColumns = @JoinColumn(name = "CUSTOMER_ITEM_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<Item> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CustomerItem{" +
                "id=" + id +
                ", qty=" + qty +
                ", customer=" + customer +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerItem that = (CustomerItem) o;
        return qty == that.qty &&
                Objects.equals(id, that.id) &&
                Objects.equals(customer, that.customer) &&
                Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, qty, customer, items);
    }
}
