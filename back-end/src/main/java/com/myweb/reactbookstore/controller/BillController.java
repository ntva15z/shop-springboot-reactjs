package com.myweb.reactbookstore.controller;

import com.myweb.reactbookstore.converter.BillConverter;
import com.myweb.reactbookstore.converter.CustomerConverter;
import com.myweb.reactbookstore.dto.BillDTO;
import com.myweb.reactbookstore.dto.CustomerDTO;
import com.myweb.reactbookstore.entity.Bill;
import com.myweb.reactbookstore.entity.BillDetail;
import com.myweb.reactbookstore.entity.Customer;
import com.myweb.reactbookstore.entity.Product;
import com.myweb.reactbookstore.repository.BillReponsitory;
import com.myweb.reactbookstore.repository.CustomerReponsitory;
import com.myweb.reactbookstore.service.CustomerService;
import com.myweb.reactbookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class BillController {
    @Autowired
    private BillReponsitory billReponsitory;
    @Autowired
    private BillConverter billConverter;
    @Autowired
    private CustomerReponsitory customerReponsitory;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public List<CustomerDTO> listCustomer (){
        List<CustomerDTO> list = new ArrayList<>();
        List<Customer> listCustomer = customerReponsitory.findAll();
        for(Customer c:listCustomer){
            CustomerDTO dto = customerConverter.toCustomerDTO(c);
            list.add(dto);
        }
        return list;
    }

    @GetMapping("/bill/list")
    public List<BillDTO> listBill(){
        List<BillDTO> list = new ArrayList<>();
        List<Bill> listBill = billReponsitory.findAll();
        for (Bill b:listBill){
            BillDTO dto = billConverter.toBillDTO(b);
            list.add(dto);
        }
        return list;
    }

    @PutMapping("/bill/active")
    public BillDTO activeBill(@RequestParam("id") long id){
        Optional<Bill> opt = billReponsitory.findById(id);
        if(opt.isPresent()){
            opt.get().setStatus(2);
            opt.get().setActivedate(Calendar.getInstance().getTime());
            for(BillDetail b:opt.get().getBillDetailList()){
                Optional<Product> optPro = productService.findById(b.getProduct().getId());
                if(optPro.isPresent()){
                    optPro.get().setQuantity(optPro.get().getQuantity()-b.getQuantity());
                    productService.save(optPro.get());
                }
            }
            billReponsitory.save(opt.get());
        }
        return billConverter.toBillDTO(opt.get());
    }

    @DeleteMapping("/bill/delete")
    public BillDTO deleteBill(@RequestParam("id")long id){
        Optional<Bill> opt = billReponsitory.findById(id);
        if(opt.isPresent()){
            opt.get().setStatus(3);
        }
        billReponsitory.save(opt.get());
        return billConverter.toBillDTO(opt.get());
    }
}
