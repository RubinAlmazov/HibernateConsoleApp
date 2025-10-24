package com.hibernate;

import com.hibernate.services.ClientService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.hibernate");

        SessionFactory sessionFactory = context.getBean(SessionFactory.class);

        ClientService Clientservice = context.getBean(ClientService.class);

        Console console = context.getBean(Console.class);

        Client client = console.createClient();

        Profile profile = new Profile("astra", "112", client);

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.persist(profile);
        session.getTransaction().commit();

    }
}
