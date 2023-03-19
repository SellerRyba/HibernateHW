
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.example.crud.ClientCrudService;
import org.example.crud.PlanetCrudService;
import org.example.crud.TicketCrudService;
import org.example.entity.Client;
import org.example.entity.Planet;
import org.example.entity.Ticket;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicketCrudServiceTest {

    private SessionFactory sessionFactory;
    private Session session;
    private TicketCrudService ticketService;
    private final LocalDateTime CREATED_AT = LocalDateTime.now(ZoneId.of("UTC"));
    private Client client;
    private Planet toPlanet;
    private Planet fromPlanet;
    private Ticket ticket;
    private Ticket ticket1;
    private int ticketId1;
    private int ticketId;


    @BeforeEach
    public void setUp() {
        sessionFactory = HibernateUtil.getInstance();
        session = sessionFactory.openSession();
        ticketService = new TicketCrudService();
        client = new ClientCrudService().getById(9);
        toPlanet = new PlanetCrudService().getById("VEN");
        fromPlanet = new PlanetCrudService().getById("MARS");
        ticket = new Ticket();
        ticket.setFromPlanet(fromPlanet);
        ticket.setToPlanet(toPlanet);
        ticket.setCreatedAt(CREATED_AT);
        ticket.setClient(client);
        ticketId = ticketService.create(ticket);
        ticket1 = new Ticket();
        ticket1.setClient(client);
        ticket1.setCreatedAt(CREATED_AT);
        ticket1.setFromPlanet(new PlanetCrudService().getById("JUP"));
        ticket1.setToPlanet(new PlanetCrudService().getById("MARS"));
        ticketId1 = ticketService.create(ticket1);

    }

    @AfterEach
    public void tearDown() {
        if (ticketService.getByIdTicket(ticketId) != null) {
            session.beginTransaction();
            session.remove(ticket);
            session.remove(ticket1);
            session.getTransaction().commit();
            session.close();
        } else {
            session.beginTransaction();
            session.remove(ticket1);
            session.getTransaction().commit();
            session.close();

        }
        session.close();
    }

    @Test
    public void testCreate() {
        // Given

        // When
        int id = ticketId;

        // Then
        assertTrue(id > 0);

    }

    @Test
    public void testGetByIdTicket() {
        // Given
        Ticket ticketCreated = ticket;
        int id = ticketId;

        // When
        Ticket retrievedTicket = ticketService.getByIdTicket(id);

        // Then
        assertNotNull(retrievedTicket);
        assertEquals(ticketCreated.getId(), retrievedTicket.getId());
        assertEquals(ticketCreated.getClient().getId(), retrievedTicket.getClient().getId());
        assertEquals(ticketCreated.getFromPlanet().getId(), retrievedTicket.getFromPlanet().getId());
        assertEquals(ticketCreated.getToPlanet().getId(), retrievedTicket.getToPlanet().getId());


    }

    @Test
    public void testGetAllTicketByIdClient() {
        // Given
        Ticket ticketFirst = ticket;
        Ticket ticketSecond = ticket1;

        // When
        List<Ticket> tickets = ticketService.getAllTicketByIdClient(client);

        // Then
        assertNotNull(tickets);
        assertEquals(3, tickets.size());
        assertEquals(tickets.get(1).getId(), ticketFirst.getId());
        assertEquals(tickets.get(2).getId(), ticketSecond.getId());


    }

    //
    @Test
    public void testUpdate() {
        // Given
        Ticket retrievedTicket = ticketService.getByIdTicket(ticketId);
        retrievedTicket.setToPlanet(new PlanetCrudService().getById("JUP"));

        // When
        ticketService.update(retrievedTicket);

        // Then
        Ticket updatedTicket = ticketService.getByIdTicket(ticketId);
        assertNotNull(updatedTicket);
        assertEquals(retrievedTicket.getClient().getId(), updatedTicket.getClient().getId());
    }

    @Test
    public void testDelete() {
        // Given
        int id = ticketId;

        // When
        ticketService.delete(id);

        // Then
        Ticket deletedTicket = ticketService.getByIdTicket(id);
        assertNull(deletedTicket);
    }

}

