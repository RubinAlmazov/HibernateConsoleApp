package com.hibernate.services;

import com.hibernate.Client;
import com.hibernate.Order;
import com.hibernate.OrderStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Random;

@Service
public class OrderService {


    private final SessionFactory sessionFactory;

    public OrderService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Order addOrder(Order order, int clientId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Client client = session.getReference(Client.class, clientId);
            order.setClient(client);

            session.persist(order);
            session.getTransaction().commit();
            return order;
        }
    }

    public Order findOrder(int orderId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Order.class,orderId);
        }
    }
}
