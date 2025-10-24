package com.hibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "address")
    String address;

    @Column(name = "phone")
    String phone;

    @OneToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    Client client;

    public Profile() {
    }

    public Profile(String address, String phone, Client client) {
        this.address = address;
        this.phone = phone;
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
