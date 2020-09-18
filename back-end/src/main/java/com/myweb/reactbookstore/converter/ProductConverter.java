package com.myweb.reactbookstore.converter;

import com.myweb.reactbookstore.dto.ProductDTO;
import com.myweb.reactbookstore.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product toProduct(ProductDTO dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setImage(dto.getImage());
        product.setQuantity(dto.getQuantity());
        product.setPrice(dto.getPrice());
        product.setManufacturedDate(dto.getManufacturedDate());
        product.setDescription(dto.getDescription());
        product.setStatus(dto.isStatus());
        return product;
    }

    public ProductDTO toProductDTO(Product product){
        ProductDTO dto = new ProductDTO();
        if(product.getId()!=null){
            dto.setId(product.getId());
        }
        dto.setCategoryId(product.getCategory().getId());
        dto.setName(product.getName());
        dto.setImage(product.getImage());
        dto.setQuantity(product.getQuantity());
        dto.setPrice(product.getPrice());
        dto.setManufacturedDate(product.getManufacturedDate());
        dto.setDescription(product.getDescription());
        dto.setStatus(product.isStatus());
        return dto;
    }

    public Product toProduct(ProductDTO dto,Product product){
        product.setName(dto.getName());
        product.setImage(dto.getImage());
        product.setQuantity(dto.getQuantity());
        product.setPrice(dto.getPrice());
        product.setManufacturedDate(dto.getManufacturedDate());
        product.setDescription(dto.getDescription());
        product.setStatus(dto.isStatus());
        return product;
    }
}
