package com.hibernate.services;

import com.hibernate.Client;
import com.hibernate.Coupon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponService {
    private final SessionFactory sessionFactory;

    public CouponService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void editCoupon(String code, int discount, int couponId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Coupon coupon = session.getReference(Coupon.class, couponId);
            coupon.setCode(code);
            coupon.setDiscount(discount);

            session.merge(coupon);
            session.getTransaction().commit();
        }
    }


    public Coupon createCoupon(Coupon coupon) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<Client> attached = coupon.getClientList().stream()
                    .map(c -> session.getReference(Client.class, c.getId()))
                    .peek(c -> c.getCouponList().add(coupon))
                    .collect(Collectors.toList());

            coupon.setClientList(attached);

            session.persist(coupon);
            session.getTransaction().commit();
            return coupon;
        }
    }

    public List<Integer> allCoupons(int clientId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "SELECT co.id FROM Coupon co JOIN co.clientList c WHERE c.id = :cid",
                            Integer.class)
                    .setParameter("cid", clientId)
                    .list();
        }
    }



}
