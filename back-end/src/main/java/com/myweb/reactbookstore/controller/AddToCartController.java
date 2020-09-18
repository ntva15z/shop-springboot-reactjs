package com.myweb.reactbookstore.controller;

import com.myweb.reactbookstore.converter.CartDetailConverter;
import com.myweb.reactbookstore.converter.CustomerConverter;
import com.myweb.reactbookstore.dto.CartDetailDTO;
import com.myweb.reactbookstore.dto.CustomerDTO;
import com.myweb.reactbookstore.entity.*;
import com.myweb.reactbookstore.repository.BillDetailReponsitory;
import com.myweb.reactbookstore.repository.BillReponsitory;
import com.myweb.reactbookstore.repository.CartDetailReponsitory;
import com.myweb.reactbookstore.repository.CartReponsitory;
import com.myweb.reactbookstore.service.CartDetailService;
import com.myweb.reactbookstore.service.CartService;
import com.myweb.reactbookstore.service.CustomerService;
import com.myweb.reactbookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/home")
@CrossOrigin
public class AddToCartController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CartReponsitory cartReponsitory;

    @Autowired
    private CartDetailReponsitory cartDetailReponsitory;
    @Autowired
    private CartDetailConverter cartDetailConverter;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BillDetailReponsitory billDetailReponsitory;
    @Autowired
    private BillReponsitory billReponsitory;


    @PostMapping("/addtocart/{id}/{quantity}")
    public String addToCart(@PathVariable("id") long id, @PathVariable("quantity") int quantity, @RequestBody CustomerDTO dto) {
        String result = null;

        Optional<Customer> customer = customerService.findById(dto.getId());
        if (customer.isPresent()) {
            Optional<Product> optProduct = productService.findById(id);
            if (optProduct.isPresent()) {
                Cart carts = cartReponsitory.findByCustomer(customer.get().getId());
                if (carts == null) {
                    if(optProduct.get().getQuantity()>quantity){
                        Cart entity = new Cart();
                        CartDetail cartDetail = new CartDetail();
                        entity.setCustomer(customer.get());
                        cartService.save(entity);
                        cartDetail.setCart(entity);
                        cartDetail.setProduct(optProduct.get());
                        cartDetail.setQuantity(quantity);
                        cartDetailService.save(cartDetail);
                        result = "success";
                    }
                    else{
                        result = "fail";
                    }
                } else {
                    boolean check = false;
                    for (CartDetail c : carts.getCartDetailList()) {
                        if (c.getProduct().getId().equals(optProduct.get().getId())) {
                            if(optProduct.get().getQuantity()-c.getQuantity()-quantity>0){
                                c.setQuantity(c.getQuantity() + quantity);
                                cartDetailService.save(c);
                                result = "success";
                            }
                            else{
                                result="fail";
                            }
                            check = true;
                        }
                    }
                    if (check == false) {
                        if(optProduct.get().getQuantity()>quantity){
                            CartDetail cartDetail1 = new CartDetail();
                            cartDetail1.setCart(carts);
                            cartDetail1.setProduct(optProduct.get());
                            cartDetail1.setQuantity(quantity);
                            cartDetailService.save(cartDetail1);
                            result = "success";
                        }
                        else{
                            result = "fail";
                        }
                    }
                }
            }
        } else {
            result = "fail";
        }

        return result;
    }

    @PostMapping("/viewcart")
    public List<CartDetailDTO> viewCart(@RequestBody CustomerDTO dto) {
        List<CartDetailDTO> listDTO = new ArrayList<>();
        Optional<Customer> customer = customerService.findById(dto.getId());
        if (customer.isPresent()) {
            List<CartDetail> list = cartDetailReponsitory.findByCustomer(customer.get().getId());
            for (CartDetail c : list) {
                CartDetailDTO d = cartDetailConverter.toCartDetailDTO(c);
                listDTO.add(d);
            }
        }
        return listDTO;
    }

    @PutMapping("/viewcart")
    public String update(@RequestParam("id") long id, @RequestParam("quantity") int quantity) {
        String msg = "fail";
        Optional<CartDetail> cartDetail = cartDetailService.findById(id);
        if (cartDetail.isPresent()) {
            Optional<Product> product = productService.findById(cartDetail.get().getProduct().getId());
            if (product.isPresent()) {
                if (quantity < product.get().getQuantity()) {
                    cartDetail.get().setQuantity(quantity);
                    cartDetailService.save(cartDetail.get());
                    msg = "success";
                }
            }
        }
        return msg;
    }


    @DeleteMapping("/viewcart")
    public String delete(@RequestParam("id") long id) {
        String msg = "fail";
        Optional<CartDetail> cartDetail = cartDetailService.findById(id);
        if (cartDetail.isPresent()) {
            cartDetailService.deleteById(id);
            msg = "success";
        }
        return msg;
    }

    @PostMapping("/checkout")
    public Double getTotal(@RequestBody CustomerDTO dto) {
        double total = 0;
        Optional<Customer> customer = customerService.findById(dto.getId());
        if (customer.isPresent()) {
            total = cartReponsitory.total(customer.get().getId());
        }
        return total;
    }

    @PostMapping("/checkout/submit")
    public String checkOutSubmit(@RequestParam("address") String address, @RequestBody CustomerDTO dto) {
        String msg = "Fail";
        Optional<Customer> customer = customerService.findById(dto.getId());
        if (customer.isPresent()) {
            List<CartDetail> listcartdetail = cartDetailReponsitory.findByCustomer(customer.get().getId());
            if (listcartdetail.isEmpty()) {
                msg = "Fail,Cart is empty!";
            } else {
                boolean check = true;
                for (CartDetail c : listcartdetail) {
                    if (c.getQuantity() > c.getProduct().getQuantity()) {
                        msg = "Fail,quantity in excess of the number of products in stock!";
                        check = false;
                    }
                }
                if (check) {
                    Double total = cartReponsitory.total(customer.get().getId());
                    Bill bill = new Bill();
                    bill.setCustomer(customer.get());
                    bill.setOrderdate(Calendar.getInstance().getTime());
                    bill.setTotal(total);
                    bill.setAddress(address);
                    bill.setStatus(1);
                    billReponsitory.save(bill);
                    for (CartDetail c : listcartdetail) {
                        BillDetail billDetail = new BillDetail();
                        billDetail.setBill(bill);
                        billDetail.setProduct(c.getProduct());
                        billDetail.setQuantity(c.getQuantity());
                        billDetailReponsitory.save(billDetail);
                        cartDetailService.deleteById(c.getId());
                    }
                    msg = "success";
                }
            }
        }
        return msg;
    }

}
