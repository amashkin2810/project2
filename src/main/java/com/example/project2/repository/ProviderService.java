package com.example.project2.repository;

import com.example.project2.entity.Provider;

import java.util.List;

public interface ProviderService {
    List<Provider> findAll();
    Provider findByid(Long id);
    Provider findByName(String name);
    Provider save(Provider provider);
}
