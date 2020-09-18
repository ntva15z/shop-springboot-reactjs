package com.myweb.reactbookstore.service;


import com.myweb.reactbookstore.entity.Cart;

import java.util.List;
import java.util.Optional;

public interface CartService {
    Cart save(Cart entity);

    Optional<Cart> findById(Long id);

    List<Cart> findAll();

    long count();

    void deleteById(Long id);
}
