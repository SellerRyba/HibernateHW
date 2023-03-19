package org.example.crud;

import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class TicketCrudService {
    public int create(Ticket ticket){
        int id = 0;
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Client client = session.get(Client.class, ticket.getClient().getId());
        Planet toPlanet = session.get(Planet.class, ticket.getToPlanet().getId());
        Planet fromPlanet = session.get(Planet.class, ticket.getFromPlanet().getId());
        if (client != null && toPlanet != null && fromPlanet != null){
            Transaction transaction = session.beginTransaction();
            session.persist(ticket);
            transaction.commit();
            session.close();
            id = ticket.getId();
        }
        return id;
    }

    public Ticket getByIdTicket(int id){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Ticket ticket = session.get(Ticket.class, id);
        session.close();
        return ticket;
    }
    public List<Ticket> getAllTicketByIdClient(Client client){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Client clientSet = session.get(Client.class, client.getId());
        List<Ticket> list = session.createQuery("FROM Ticket t WHERE t.client = :client",
                        Ticket.class)
                .setParameter("client", client)
                .list();
        clientSet.setTickets(list);
        session.close();
        return list;
    }

    public void update(Ticket ticket){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(ticket);
        transaction.commit();
        session.close();
    }

    public void delete(int id){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(getByIdTicket(id));
        transaction.commit();
        session.close();
    }

    public void deleteAllByClient(Client client){
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createNativeQuery("DELETE FROM Ticket WHERE client_id = :clientId",
                        Ticket.class)
                .setParameter("clientId", client.getId())
                .executeUpdate();
        transaction.commit();
        session.close();
    }

}
