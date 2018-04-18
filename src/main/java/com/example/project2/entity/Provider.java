package com.example.project2.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "provider")
@NamedQueries({
        @NamedQuery(name = "Provider.findByName", query = "select p from Provider p where p.name = :name")
})
public class Provider implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column (name = "ID")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int tin;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "provider", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<ProviderItem> providerItems = new ArrayList<>();

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

    public int getTin() {
        return tin;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }

    public List<ProviderItem> getProviderItems() {
        return providerItems;
    }

    public void setProviderItems(List<ProviderItem> providerItems) {
        this.providerItems = providerItems;
    }

    public void addProviderItem(ProviderItem providerItem) {
        providerItem.setProvider(this);
        getProviderItems().add(providerItem);
    }

    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tin=" + tin +
                ", providerItems=" + providerItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Objects.equals(id, provider.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
