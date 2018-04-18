package com.example.project2.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


import java.io.Serializable;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "warehouse")
public class Warehouse implements Serializable{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column (name = "ID")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
   // @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "warehouse", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<WarehouseItem> warehouseItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<WarehouseItem> getWarehouseItems() {
        return warehouseItems;
    }

    public void setWarehouseItems(List<WarehouseItem> warehouseItems) {
        this.warehouseItems = warehouseItems;
    }
    public void addWarehouseItem(WarehouseItem warehouseItem) {
        warehouseItem.setWarehouse(this);
        getWarehouseItems().add(warehouseItem);
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", warehouseItems=" + warehouseItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(id, warehouse.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
