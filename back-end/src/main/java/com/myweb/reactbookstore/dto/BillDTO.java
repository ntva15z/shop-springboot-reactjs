package com.myweb.reactbookstore.dto;

import com.myweb.reactbookstore.entity.Bill;

import java.util.Date;

public class BillDTO {
    private long id;
    private long customerId;
    private Date orderdate;
    private Date activedate;
    private String address;
    private double total;
    private int status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getActivedate() {
        return activedate;
    }

    public void setActivedate(Date activedate) {
        this.activedate = activedate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
