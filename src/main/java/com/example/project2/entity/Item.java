package com.example.project2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column (name = "ID")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Float price;
    @Column(length = 200,nullable = false)
    private String description;
    @JsonIgnore
    @ManyToMany
    @JoinTable (name = "provider_item_item",
    joinColumns = @JoinColumn (name = "ITEM_ID2"),
    inverseJoinColumns = @JoinColumn (name = "PROVIDER_ITEM_ID2"))
    private List<ProviderItem> providerItems =  new ArrayList<>();
    @JsonIgnore
    @ManyToMany
    @JoinTable (name = "warehouse_item_item",
            joinColumns = @JoinColumn (name = "ITEM_ID3"),
            inverseJoinColumns = @JoinColumn (name = "WAREHOUSE_ITEM_ID3"))
    private List<WarehouseItem> warehouseItems =  new ArrayList<>();

    public Item(String name, Float price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Item() {
    }

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProviderItem> getProviderItems() {
        return providerItems;
    }

    public void setProviderItems(List<ProviderItem> providerItems) {
        this.providerItems = providerItems;
    }

    public List<WarehouseItem> getWarehouseItems() {
        return warehouseItems;
    }

    public void setWarehouseItems(List<WarehouseItem> warehouseItems) {
        this.warehouseItems = warehouseItems;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
