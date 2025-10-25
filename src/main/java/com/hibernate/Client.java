package com.hibernate;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "registrationDate")
    private LocalDateTime registrationDate;

    @OneToOne(mappedBy = "client", cascade =
            {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REMOVE})
    private Profile profile;


    @OneToMany(mappedBy = "client", cascade =
            {CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REMOVE})
    private List<Order> orderList;

    @ManyToMany
    @JoinTable(
            name = "client_coupons",
            joinColumns = @JoinColumn(name = "client_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    )
    private List<Coupon> couponList = new ArrayList<>();

    public Client() {
    }

    public Client(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Order> getOrders() {
        return orderList;
    }

    public void setOrders(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }
}
