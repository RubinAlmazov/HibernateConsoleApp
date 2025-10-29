package com.hibernate.services;

import com.hibernate.Client;
import com.hibernate.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final SessionFactory sessionFactory;

    public ProfileService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void editProfile(int clientId, String address, String phone) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Client client = session.find(Client.class, clientId);
            client.getProfile().setAddress(address);
            client.getProfile().setPhone(phone);
            session.merge(client);
            session.getTransaction().commit();
        }
    }

    public void addProfile(Profile profile) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(profile);
            session.getTransaction().commit();
        }
    }
}
