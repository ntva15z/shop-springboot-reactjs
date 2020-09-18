package com.myweb.reactbookstore.converter;

import com.myweb.reactbookstore.dto.CategoryDTO;
import com.myweb.reactbookstore.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public Category toCategory(CategoryDTO dto){
        Category category = new Category();
        category.setName(dto.getName());
        category.setStatus(dto.isStatus());
        return category;
    }

    public CategoryDTO toCategoryDTO(Category category){
        CategoryDTO dto = new CategoryDTO();
        if(category.getId()!=null){
            dto.setId(category.getId());
        }
        dto.setName(category.getName());
        dto.setStatus(category.isStatus());
        return  dto;
    }

    public Category toCategory(CategoryDTO dto,Category category){
        category.setName(dto.getName());
        category.setStatus(dto.isStatus());
        return category;
    }
}
