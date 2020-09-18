package com.myweb.reactbookstore.converter;

import com.myweb.reactbookstore.dto.CartDTO;
import com.myweb.reactbookstore.entity.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartConverter {
    public CartDTO toCartDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        if (cart.getId() != null) {
            dto.setId(cart.getId());
        }
        dto.setCustomerId(cart.getCustomer().getId());
        return dto;
    }
}
