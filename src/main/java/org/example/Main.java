package org.example;

import org.example.crud.ClientCrudService;
import org.example.crud.PlanetCrudService;
import org.example.crud.TicketCrudService;
import java.time.LocalDateTime;
import java.time.ZoneId;


public class Main {
    public static void main(String[] args) {
        ClientCrudService clientCrudService = new ClientCrudService();
        PlanetCrudService planetCrudService = new PlanetCrudService();
        TicketCrudService ticketCrudService = new TicketCrudService();
        LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("UTC"));


    }
}