package com.myweb.reactbookstore.converter;

import com.myweb.reactbookstore.dto.CustomerDTO;
import com.myweb.reactbookstore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {
    public Customer toCustomer(CustomerDTO dto){
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setGender(dto.isGender());
        customer.setDOB(dto.getDob());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setPassword(dto.getPassword());
        return customer;
    }
    public CustomerDTO toCustomerDTO(Customer customer){
        CustomerDTO dto = new CustomerDTO();
        if(customer.getId()!=null){
            dto.setId(customer.getId());
        }
        dto.setName(customer.getName());
        dto.setGender(customer.isGender());
        dto.setDob(customer.getDOB());
        dto.setEmail(customer.getEmail());
        dto.setPhone(customer.getPhone());
        dto.setPassword(customer.getPassword());
        return dto;
    }
    public Customer toCustomer(CustomerDTO dto,Customer customer){
        customer.setName(dto.getName());
        customer.setGender(dto.isGender());
        customer.setDOB(dto.getDob());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setPassword(dto.getPassword());
        return customer;
    }
}
