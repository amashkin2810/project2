package com.example.project2.repository;

import com.example.project2.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    List<Warehouse> findAll();
    Warehouse findByid(Long id);
    Warehouse save(Warehouse warehouse);
}
