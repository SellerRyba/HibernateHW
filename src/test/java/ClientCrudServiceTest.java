import org.example.crud.ClientCrudService;
import org.example.entity.Client;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientCrudServiceTest {
    private SessionFactory sessionFactory;
    private Session session;
    private ClientCrudService clientCrudService;
    private Client client;
    private int id;

    @BeforeEach
    public void setup() {
        sessionFactory = HibernateUtil.getInstance();
        session = sessionFactory.openSession();
        clientCrudService = new ClientCrudService();
        client = new Client();
        client.setName("John");
        id = clientCrudService.create(client);
    }

    @AfterEach
    public void cleanup() {
        if (clientCrudService.getById(id) != null) {
            session.beginTransaction();
            session.remove(clientCrudService.getById(id));
            session.getTransaction().commit();
            session.close();
        }
        session.close();
    }


    @Test
    public void testCreate() {
        // Given

        // When


        // Then
        assertTrue(id > 0);

    }

    @Test
    public void testGetById() {
        // Given


        // When
        Client clientFromDb = clientCrudService.getById(client.getId());

        // Then
        assertNotNull(clientFromDb);
        assertEquals(id, clientFromDb.getId());

    }

    @Test
    public void testUpdate() {
        // Given

        // When
        Client clientToUpdate = clientCrudService.getById(id);
        clientToUpdate.setName("Olivia");
        clientCrudService.update(clientToUpdate);
        Client updatedClient = clientCrudService.getById(id);

        //Then
        assertNotNull(updatedClient);
        assertEquals("Olivia", updatedClient.getName());

    }


    @Test
    public void testDelete() {
        // Given

        // When
        clientCrudService.delete(id);
        Client deletedClient = clientCrudService.getById(id);

        // Then
        assertNull(deletedClient);
    }

}
