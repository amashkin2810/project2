package com.example.project2.config;


import com.example.project2.controller.WarehouseController;
import com.example.project2.repository.*;
import com.example.project2.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class WarehouseControllerConfig {
    @Autowired
    EntityManager em;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Bean
    ProviderService providerService() {
        return new ProviderServiceImpl(providerRepository, em);
    }

    @Bean
    ItemService itemService() {
        return new ItemServiceImpl(itemRepository);
    }
    @Bean
    WarehouseService warehouseService(){
        return new WarehouseServiceImpl(warehouseRepository);
    }
    @Bean
    CustomerService customerService(){
        return new CustomerServiceImpl(customerRepository, em);
    }
    @Bean
    TransferService transferService(){
       return new TransferService(warehouseService(),providerService(),customerService());
    }

    @Bean
    WarehouseController warehouseController(){
        return new WarehouseController(warehouseService(),providerService(),itemService(),transferService());
    }
}
