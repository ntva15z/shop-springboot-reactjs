package com.myweb.reactbookstore.controller;

import com.myweb.reactbookstore.converter.CustomerConverter;
import com.myweb.reactbookstore.dto.CustomerDTO;
import com.myweb.reactbookstore.entity.Customer;
import com.myweb.reactbookstore.entity.Role;
import com.myweb.reactbookstore.service.CustomerService;
import com.myweb.reactbookstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@CrossOrigin
public class CustomerController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerConverter customerConverter;

    @PostMapping("/register")
    public String registration(@RequestBody Customer customer) {
        String result = null;
        Customer c = customerService.findByEmail(customer.getEmail());
        if (c == null) {
            customer.setRegisterdate(Calendar.getInstance().getTime());
            Set<Role> roles = new HashSet<>();
            Optional<Role> optRole = roleService.findById(Long.parseLong(String.valueOf(2)));
            if (optRole.isPresent()) {
                roles.add(optRole.get());
                customer.setRoleList(roles);
            }
            customerService.save(customer);
            result = "Register Success";
        } else {
            result = null;
        }
        return result;
    }

    @PostMapping("/login")
    public Map<Integer, CustomerDTO> login(@RequestBody CustomerDTO dto) {
        Map<Integer, CustomerDTO> map = new HashMap<>();
        int a;
        Customer customer = customerService.findByEmail(dto.getEmail());
        if (customer != null) {
            if (customer.getPassword().equals(dto.getPassword())) {
                for (Role role : customer.getRoleList()) {
                    if (role.getId().equals(Long.parseLong(String.valueOf(1)))) {
                        dto = customerConverter.toCustomerDTO(customer);
                        a = 1;
                        map.put(a, dto);
                    } else {
                        dto = customerConverter.toCustomerDTO(customer);
                        a = 2;
                        map.put(a, dto);
                    }
                }
            } else {
                dto = null;
            }
        } else {
            dto = null;
        }
        return map;
    }

}
