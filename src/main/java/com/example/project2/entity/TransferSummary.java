package com.example.project2.entity;

import java.io.Serializable;


public class TransferSummary implements Serializable {
    private Long idEntity;
    private String nameEntity;
    private Long idEntityItem;
    private Long idItem;
    private String nameItem;
    private int qty;

    public TransferSummary() {
    }

    public TransferSummary(Long idEntity, String nameEntity, Long idEntityItem, Long idItem, String nameItem, int qty) {
        this.idEntity = idEntity;
        this.nameEntity = nameEntity;
        this.idEntityItem = idEntityItem;
        this.idItem = idItem;
        this.nameItem = nameItem;
        this.qty = qty;
    }

    public Long getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(Long idEntity) {
        this.idEntity = idEntity;
    }

    public String getNameEntity() {
        return nameEntity;
    }

    public void setNameEntity(String nameEntity) {
        this.nameEntity = nameEntity;
    }

    public Long getIdEntityItem() {
        return idEntityItem;
    }

    public void setIdEntityItem(Long idEntityItem) {
        this.idEntityItem = idEntityItem;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "TransferSummary{" +
                "idEntity=" + idEntity +
                ", nameEntity='" + nameEntity + '\'' +
                ", idEntityItem=" + idEntityItem +
                ", idItem=" + idItem +
                ", nameItem='" + nameItem + '\'' +
                ", qty=" + qty +
                '}';
    }

}
