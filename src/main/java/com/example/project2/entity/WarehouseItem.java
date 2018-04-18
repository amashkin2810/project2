package com.example.project2.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "warehouse_item")
public class WarehouseItem implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "WAREHOUSE_ITEM_ID")
    private Long id;
    @Column
    private int qty;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "WAREHOUSE_ID")
    private Warehouse warehouse;
    //@JsonIgnore
    @ManyToMany
    @JoinTable(name = "warehouse_item_item",
            joinColumns = @JoinColumn(name = "WAREHOUSE_ITEM_ID3"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID3"))
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

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        }

    @Override
    public String toString() {
        return "WarehouseItem{" +
                "id=" + id +
                ", qty=" + qty +
                ", warehouse=" + warehouse.getId() +
                ", items=" + items.toArray()[0] +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseItem that = (WarehouseItem) o;
        return Objects.equals(warehouse, that.warehouse);
    }

    @Override
    public int hashCode() {

        return Objects.hash(warehouse);
    }
}
