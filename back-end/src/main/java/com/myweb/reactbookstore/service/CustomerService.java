package com.myweb.reactbookstore.service;


import com.myweb.reactbookstore.entity.Customer;

import java.util.Optional;

public interface CustomerService  {
    void save(Customer entity);
    Customer findByEmail(String email);
    Optional<Customer> findById(Long id);
}
