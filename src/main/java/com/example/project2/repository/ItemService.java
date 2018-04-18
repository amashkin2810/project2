package com.example.project2.repository;

import com.example.project2.entity.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAll();
    Item findByid(Long id);
    Item save(Item item);
}
