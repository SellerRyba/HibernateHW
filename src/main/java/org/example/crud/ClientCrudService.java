package org.example.crud;

import org.example.entity.Client;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ClientCrudService {
    public void create(Client client) {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(client);
        transaction.commit();
        session.close();
    }

    public Client getById(int id) {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Client client = session.get(Client.class, id);
        session.close();
        return client;
    }

    public void update(Client client) {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Client clientUpdate = session.get(Client.class, client.getId());
        clientUpdate.setName(client.getName());
        Transaction transaction = session.beginTransaction();
        session.merge(clientUpdate);
        transaction.commit();
        session.close();
    }

    public void delete(int id) {
        SessionFactory sessionFactory = HibernateUtil.getInstance();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.remove(getById(id));
        transaction.commit();
        session.close();
    }
}
