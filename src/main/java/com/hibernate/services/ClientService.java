package com.hibernate.services;

import com.hibernate.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Client getClient(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Client.class, id);
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

    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Client clientForDelete = session.find(Client.class, id);
            session.remove(clientForDelete);
            session.getTransaction().commit();

        }
    }


    public List<Client> getListOfClients(int couponId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "SELECT DISTINCT c " +
                                    "FROM Client c " +
                                    "JOIN c.couponList co " +
                                    "LEFT JOIN FETCH c.profile " +
                                    "WHERE co.id = :couponId",
                            Client.class)
                    .setParameter("couponId", couponId)
                    .list();
        }
    }

}
