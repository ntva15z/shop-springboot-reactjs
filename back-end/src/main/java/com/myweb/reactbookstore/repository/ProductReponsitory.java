package com.myweb.reactbookstore.repository;

import com.myweb.reactbookstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReponsitory extends JpaRepository<Product,Long> {
    @Query("select p from Product p where p.name like CONCAT ('%',:search,'%') ")
    List<Product> findAllByName(@Param("search") String search);
}
