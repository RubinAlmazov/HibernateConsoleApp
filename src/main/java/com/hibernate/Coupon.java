package com.hibernate;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "coupons")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "discount")
    private int discount;

    @ManyToMany(mappedBy = "couponList")
    List<Client> clientList;

    public Coupon() {
    }

    public Coupon(int id, String code, int discount, List<Client> clientList) {
        this.id = id;
        this.code = code;
        this.discount = discount;
        this.clientList = clientList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}
