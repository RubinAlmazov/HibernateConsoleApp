//TODO: add scanner.nextLine(); after scanner.nextInt(). Change persist to merge in services, if needed

package com.hibernate;

import com.hibernate.services.ClientService;
import com.hibernate.services.CouponService;
import com.hibernate.services.OrderService;
import com.hibernate.services.ProfileService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@Component
public class Console {
    private final ClientService clientService;
    private final ProfileService profileService;
    private final CouponService couponService;
    private final OrderService orderService;


    public Console(ClientService clientService, ProfileService profileService, CouponService couponService, OrderService orderService) {
        this.clientService = clientService;
        this.profileService = profileService;
        this.couponService = couponService;
        this.orderService = orderService;
    }

    public void starter() {
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println(
                    "Choose action:\n" +
                    "1 - create client\n" +
                    "2 - get client\n" +
                    "3 - delete client\n" +
                    "4 - create profile\n" +
                    "5 - update profile\n" +
                    "6 - create coupon\n" +
                    "7 - update coupon\n" +
                    "8 - create order\n" +
                    "9 - find order" );
            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    createClient();
                    break;
                case 2:
                    getClientById();
                    break;
                case 3:
                    deleteClient();
                    break;
                case 4:
                    createProfile();
                    break;
                case 5:
                    updateClientsProfile();
                    break;
                case 6:
                    createCoupon();
                    break;
                case 7:
                    updateCoupon();
                    break;
                case 8:
                    createOrder();
                    break;
                case 9:
                    findOrder();
                    break;
                default:
                    System.out.println("Incorrect input");
            }

        }

    }

    public Client createClient() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter email: ");
            String email = scanner.nextLine().trim();

            Client client = new Client(LocalDateTime.now());
            client.setName(name);
            client.setEmail(email);

            clientService.save(client);

            return client;
        }
    }


    public Client getClientById() {
        try (Scanner scanner = new Scanner(System.in)) {

            return clientService.getClient(scanner.nextInt());
        }
    }

    public void deleteClient() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter clients id: ");
            clientService.delete(scanner.nextInt());
        }
    }

    public Profile createProfile() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter clients id: ");
            Client client  = clientService.getClient(scanner.nextInt());
            scanner.nextLine();

            System.out.println("Enter address: ");
            String address = scanner.nextLine();

            System.out.println("Enter phone number: ");
            String phone = scanner.nextLine();

            Profile profile = new Profile(address, phone, client);
            profileService.addProfile(profile);

            return profile;
        }
    }

    public void updateClientsProfile() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter clients id: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Enter address: ");
            String address = scanner.nextLine();

            System.out.println("Enter phone number: ");
            String phone = scanner.nextLine();

            profileService.editProfile(id, address, phone);

        }
    }

    public Coupon createCoupon() {
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("Enter code: ");
            String code = scanner.nextLine();

            System.out.println("Enter discount: ");
            int discount = scanner.nextInt();

            System.out.println("Enter id: ");
            List<Client> clientList = clientService.getListOfClients(scanner.nextInt());

            Coupon coupon = new Coupon(code, discount, clientList);
            couponService.createCoupon(coupon);

            return coupon;
        }
    }

    public void updateCoupon() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter code: ");
            String code = scanner.nextLine();

            System.out.println("Enter discount: ");
            int discount = scanner.nextInt();

            System.out.println("Enter client id: ");
            System.out.println(couponService.allCoupons(scanner.nextInt()));

            System.out.println("Enter coupon id: ");
            couponService.editCoupon(code, discount, scanner.nextInt());

        }
    }

    public Order createOrder() {
        try (Scanner scanner = new Scanner(System.in)){
            Random random = new Random();

            Order order = new Order(LocalDate.now(), random.nextInt(100), OrderStatus.CREATED, new Client());

            System.out.println("Enter id: ");
            orderService.addOrder(order, scanner.nextInt());

            return order;
        }
    }

    public Order findOrder() {
        try (Scanner scanner = new Scanner(System.in)){
            System.out.println("Enter id: ");

            return orderService.findOrder(scanner.nextInt());
        }
    }
}
