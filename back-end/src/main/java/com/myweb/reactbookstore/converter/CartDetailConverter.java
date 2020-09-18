package com.myweb.reactbookstore.converter;

import com.myweb.reactbookstore.dto.CartDetailDTO;
import com.myweb.reactbookstore.entity.CartDetail;
import org.springframework.stereotype.Component;

@Component
public class CartDetailConverter {
    public CartDetail toCartDetail(CartDetailDTO dto){
        CartDetail cartDetail = new CartDetail();
        cartDetail.setQuantity(dto.getQuantity());
        return cartDetail;
    }
    public CartDetailDTO toCartDetailDTO(CartDetail cartDetail){
        CartDetailDTO dto = new CartDetailDTO();
        if(cartDetail.getId()!=null){
            dto.setId(cartDetail.getId());
        }
        dto.setCartId(cartDetail.getCart().getId());
        dto.setProductId(cartDetail.getProduct().getId());
        dto.setQuantity(cartDetail.getQuantity());
        return dto;
    }
    public CartDetail toCartDetail(CartDetailDTO dto,CartDetail cartDetail){
        cartDetail.setQuantity(dto.getQuantity());
        return cartDetail;
    }
}
