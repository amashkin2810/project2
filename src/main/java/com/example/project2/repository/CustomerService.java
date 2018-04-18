package com.example.project2.repository;

import com.example.project2.entity.Customer;


import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findByid(Long id);
    Customer findByPassport(String passport);
    Customer save(Customer customer);

}
