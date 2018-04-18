package com.example.project2.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;
@Entity
@Table(name = "customer")
@NamedQueries({
           @NamedQuery(name = "Customer.findByPassport", query = "SELECT c FROM Customer c WHERE c.passport = :passport")
})
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column (name = "ID")
    private Long id;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false)
    private String passport;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "customer", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<CustomerItem> customerItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public List<CustomerItem> getCustomerItems() {
        return customerItems;
    }

    public void setCustomerItems(List<CustomerItem> customerItems) {
        this.customerItems = customerItems;
    }
    public void addCustomerItem(CustomerItem customerItem) {
        customerItem.setCustomer(this);
        getCustomerItems().add(customerItem);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", passport='" + passport + '\'' +
                ", customerItems=" + customerItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(firstname, customer.firstname) &&
                Objects.equals(lastname, customer.lastname) &&
                Objects.equals(passport, customer.passport) &&
                Objects.equals(customerItems, customer.customerItems);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstname, lastname, passport, customerItems);
    }
}
