package com.hibernate.services;

import com.hibernate.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ClientService {

    SessionFactory sessionFactory;

    public ClientService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Client client) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(client);
            session.getTransaction().commit();
        }
    }

    public Client update(Client client) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(client);
            session.getTransaction().commit();
            return client;
        }
    }

}
