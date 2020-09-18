package com.myweb.reactbookstore.service.impl;

import com.myweb.reactbookstore.entity.Category;
import com.myweb.reactbookstore.repository.CategoryRepository;
import com.myweb.reactbookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl implements CategoryService {
    @Autowired
    private CategoryRepository caterepository;

    @Override
    public Category save(Category entity) {
        return caterepository.save(entity);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return caterepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return (List<Category>)caterepository.findAll();
    }

    @Override
    public long count() {
        return caterepository.count();
    }

    @Override
    public void deleteById(Long id) {
        caterepository.deleteById(id);
    }

    @Override
    public List<Category> findByNameLikeOrderByName(String name) {
        return caterepository.findByNameLikeOrderByName("%" + name + "%");
    }

}
