package com.myweb.reactbookstore.controller;

import com.myweb.reactbookstore.converter.ProductConverter;
import com.myweb.reactbookstore.dto.ProductDTO;
import com.myweb.reactbookstore.entity.Category;
import com.myweb.reactbookstore.entity.Product;
import com.myweb.reactbookstore.repository.ProductReponsitory;
import com.myweb.reactbookstore.service.CategoryService;
import com.myweb.reactbookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductConverter productConverter;
    @Autowired
    private ProductReponsitory productReponsitory;

    @GetMapping("/list")
    public List<ProductDTO> list(){
        List<ProductDTO> list = new ArrayList<>();
        List<Product> listpro = (List<Product>)productService.findAll();
        for(Product item:listpro){
            ProductDTO newDTO =  productConverter.toProductDTO(item);
            list.add(newDTO);
        }
        return list;
    }



    @PostMapping("/add")
    public ProductDTO save(@RequestBody ProductDTO productDTO){
        Product product = productConverter.toProduct(productDTO);
        Optional<Category> optCate = categoryService.findById(productDTO.getCategoryId());
        if(optCate.isPresent()){
            product.setCategory(optCate.get());
            product = productService.save(product);
        }
        return productConverter.toProductDTO(product);
    }

    @DeleteMapping("/{id}")
    public ProductDTO delete(@PathVariable("id")long id){
        ProductDTO dto =  new ProductDTO();
        Optional<Product> opt = productService.findById(id);
        try {
            if(opt.isPresent()){
                opt.get().setStatus(false);
                productService.save(opt.get());
                dto = productConverter.toProductDTO(opt.get());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable("id")long id,@RequestBody ProductDTO productDTO){
        Product product = new Product();
        productDTO.setId(id);
        try {
            Optional<Product> opt = productService.findById(productDTO.getId());
            if(opt.isPresent()){
                product = productConverter.toProduct(productDTO,opt.get());
            }
            Optional<Category> optCate = categoryService.findById(productDTO.getCategoryId());
            if(optCate.isPresent()){
                product.setCategory(optCate.get());
            }
            productService.save(product);
        }catch (Exception e){
            e.printStackTrace();
        }
        return productConverter.toProductDTO(product);
    }

//    @PostMapping("/list")
//    public List<ProductDTO> search(@RequestParam("search")String search){
//        List<ProductDTO> list = new ArrayList<>();
//        List<Product> listPro = productReponsitory.findAllByName(search);
//        if(listPro!=null){
//            for (Product p:listPro){
//                ProductDTO dto = productConverter.toProductDTO(p);
//                list.add(dto);
//            }
//        }
//        return list;
//    }


}
