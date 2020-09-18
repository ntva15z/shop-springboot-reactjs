package com.myweb.reactbookstore.service;


import com.myweb.reactbookstore.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category save(Category entity);

    Optional<Category> findById(Long id);

    Iterable<Category> findAll();

    long count();

    void deleteById(Long id);

    List<Category> findByNameLikeOrderByName(String name);

}
