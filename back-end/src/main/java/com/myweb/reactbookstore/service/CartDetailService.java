package com.myweb.reactbookstore.service;


import com.myweb.reactbookstore.entity.CartDetail;

import java.util.List;
import java.util.Optional;

public interface CartDetailService {
    CartDetail save(CartDetail entity);

    Optional<CartDetail> findById(Long id);

    List<CartDetail> findAll();

    long count();

    void deleteById(Long id);
}
