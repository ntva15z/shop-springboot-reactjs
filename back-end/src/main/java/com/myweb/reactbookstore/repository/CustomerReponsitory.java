package com.myweb.reactbookstore.repository;

import com.myweb.reactbookstore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerReponsitory extends JpaRepository<Customer,Long> {
    @Query("SELECT c FROM  Customer  c WHERE c.email=:email" )
    Customer findByEmail(String email);

}
