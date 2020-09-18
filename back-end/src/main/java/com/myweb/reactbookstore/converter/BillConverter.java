package com.myweb.reactbookstore.converter;

import com.myweb.reactbookstore.dto.BillDTO;
import com.myweb.reactbookstore.entity.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillConverter {
    public Bill toBill(BillDTO dto){
        Bill bill = new Bill();
        bill.setOrderdate(dto.getOrderdate());
        bill.setAddress(dto.getAddress());
        bill.setStatus(dto.getStatus());
        bill.setTotal(dto.getTotal());
        return bill;
    }
    public BillDTO toBillDTO(Bill bill){
        BillDTO dto = new BillDTO();
        if(bill.getId()!=null){
            dto.setId(bill.getId());
        }
        dto.setCustomerId(bill.getCustomer().getId());
        if(bill.getActivedate()!=null){
            dto.setActivedate(bill.getActivedate());
        }
        dto.setOrderdate(bill.getOrderdate());
        dto.setAddress(bill.getAddress());
        dto.setTotal(bill.getTotal());
        dto.setStatus(bill.getStatus());
        return dto;
    }
    public Bill toBill(BillDTO dto,Bill bill){
        bill.setOrderdate(dto.getOrderdate());
        bill.setAddress(dto.getAddress());
        bill.setStatus(dto.getStatus());
        bill.setTotal(dto.getTotal());
        return bill;
    }
}
