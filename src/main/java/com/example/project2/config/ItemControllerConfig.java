package com.example.project2.config;

import com.example.project2.controller.ItemController;
import com.example.project2.repository.ItemRepository;
import com.example.project2.repository.ItemService;
import com.example.project2.repository.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemControllerConfig {
    @Autowired
    ItemRepository itemRepository;
    @Bean
    ItemService itemService(){

        return new ItemServiceImpl(itemRepository);
    }
    @Bean
    ItemController itemController(){
        return new ItemController(itemService());
    }
}
