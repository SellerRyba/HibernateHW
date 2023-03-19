package org.example.utils;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory INSTANCE;
    private HibernateUtil(){
    }
    public static SessionFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Configuration()
                    .addAnnotatedClass(Client.class)
                    .addAnnotatedClass(Planet.class)
                    .addAnnotatedClass(Ticket.class)
                    .buildSessionFactory();
        }
        return INSTANCE;
    }
}
