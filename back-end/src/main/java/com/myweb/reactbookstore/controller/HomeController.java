package com.myweb.reactbookstore.controller;

import com.myweb.reactbookstore.converter.ProductConverter;
import com.myweb.reactbookstore.dto.ProductDTO;
import com.myweb.reactbookstore.entity.Category;
import com.myweb.reactbookstore.entity.Product;
import com.myweb.reactbookstore.service.CategoryService;
import com.myweb.reactbookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home/product")
@CrossOrigin
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductConverter productConverter;

    @GetMapping("/{id}")
    public ProductDTO productDetail(@PathVariable("id") Long id){
        Optional<Product> opt = productService.findById(id);
        ProductDTO productDTO = new ProductDTO();
        if(opt.isPresent()){
            productDTO=productConverter.toProductDTO(opt.get());
        }
        return productDTO;
    }

    @GetMapping("/category/{id}")
    public List<ProductDTO> productByCategory(@PathVariable("id")long id){
        List<ProductDTO> list = new ArrayList<>();
        Optional<Category> opt = categoryService.findById(id);
        if(opt.isPresent()){
            List<Product> listProduct = opt.get().getProductList();
            for(Product item:listProduct){
                ProductDTO newDTO =  productConverter.toProductDTO(item);
                list.add(newDTO);
            }
        }
        return  list;
    }
}
