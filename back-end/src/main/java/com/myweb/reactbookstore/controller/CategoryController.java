package com.myweb.reactbookstore.controller;

import com.myweb.reactbookstore.converter.CategoryConverter;
import com.myweb.reactbookstore.dto.CategoryDTO;
import com.myweb.reactbookstore.dto.CustomerDTO;
import com.myweb.reactbookstore.entity.Category;
import com.myweb.reactbookstore.repository.CategoryRepository;
import com.myweb.reactbookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @GetMapping("/list")
    public List<CategoryDTO> list(){
        List<CategoryDTO> list = new ArrayList<>();
        List<Category> listcate = (List<Category>) categoryService.findAll();
        for(Category item:listcate){
            CategoryDTO newDTO = categoryConverter.toCategoryDTO(item);
            list.add(newDTO);
        }
        return list;
    }

    @PostMapping("/addorupdate")
    public Category saveOrUpdate(@RequestBody Category category){
        categoryService.save(category);
        return category;
    }

    @PutMapping("/update/{id}")
    public Category update(@PathVariable("id") long id,@RequestBody Category category){
        try {
            Optional<Category> opt = categoryService.findById(id);
            if(opt.isPresent()){
                opt.get().setName(category.getName());
                opt.get().setStatus(category.isStatus());
                categoryService.save(opt.get());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return category;
    }

    @DeleteMapping("/delete/{id}")
    public Category delete(@PathVariable("id") long id){
         categoryService.deleteById(id);
         return null;
    }




}
