package com.hibernate;

import com.hibernate.services.ClientService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Scanner;

@Component
public class Console {
    private final ClientService clientService;

    public Console(ClientService clientService) {
        this.clientService = clientService;
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
}
