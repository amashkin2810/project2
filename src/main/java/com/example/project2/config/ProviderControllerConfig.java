package com.example.project2.config;

import com.example.project2.controller.ProviderController;
import com.example.project2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class ProviderControllerConfig {
    @Autowired
    EntityManager em;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ProviderRepository providerRepository;
    @Bean
    ProviderService providerService() {
        return new ProviderServiceImpl(providerRepository, em);
    }

    @Bean
    ItemService itemService() {
        return new ItemServiceImpl(itemRepository);
    }

    @Bean
    ProviderController providerController() {
        return new ProviderController(providerService(), itemService());
    }
}
