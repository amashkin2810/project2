package com.example.project2.repository;

import com.example.project2.entity.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service("customerService")


@Repository
@Transactional
public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;
    private final EntityManager em;

    public CustomerServiceImpl(CustomerRepository customerRepository, EntityManager em) {
        this.customerRepository = customerRepository;
        this.em = em;
    }

    @Override
    @Transactional(readOnly=true)
    public List<Customer> findAll() {

        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly=true)
    public Customer findByid(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer findByPassport(String passport) {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByPassport", Customer.class)
                .setParameter("passport", passport);
        if (query.getResultList().size() > 0) {
            return query.getSingleResult();
        } else {
            return null;
        }
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
