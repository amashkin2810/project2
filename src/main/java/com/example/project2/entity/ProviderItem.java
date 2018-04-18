package com.example.project2.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "provider_item")
@NamedQueries({
        @NamedQuery(name = "ProviderItem.findByName", query = "select i.name from ProviderItem p JOIN p.items i")
        })
public class ProviderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "PROVIDER_ITEM_ID")
    private Long id;
    @Column
    private int qty;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "PROVIDER_ID")
    private Provider provider;
    //@JsonIgnore
    @ManyToMany
    @JoinTable(name = "provider_item_item",
            joinColumns = @JoinColumn(name = "PROVIDER_ITEM_ID2"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID2"))
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "ProviderItem{" +
                "id=" + id +
                ", qty=" + qty +
                ", provider=" + provider.getName() +
                ", items=" + items.get(0) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProviderItem that = (ProviderItem) o;
        return Objects.equals(provider, that.provider);
    }

    @Override
    public int hashCode() {

        return Objects.hash(provider);
    }
}
