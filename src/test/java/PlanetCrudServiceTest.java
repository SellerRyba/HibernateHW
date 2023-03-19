import org.example.crud.PlanetCrudService;
import org.example.entity.Planet;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlanetCrudServiceTest {
    private SessionFactory sessionFactory;
    private Session session;
    private PlanetCrudService planetCrudService;
    private Planet planet;
    private String planetID;

    @BeforeEach
    public void setup() {
        sessionFactory = HibernateUtil.getInstance();
        session = sessionFactory.openSession();
        planetCrudService = new PlanetCrudService();
        planet = new Planet();
        planet.setId("MOON");
        planet.setName("MOON");
        planetID = planetCrudService.create(planet);
    }

    @AfterEach
    public void cleanup() {
        if (planetCrudService.getById(planetID) != null){
            session.beginTransaction();
            session.remove(planet);
            session.getTransaction().commit();
            session.close();
        }
        session.close();
    }

    @Test
    public void testCreate() {
        // Given
        Planet planetCreate = planet;

        // When
        Planet getPlanet = planetCrudService.getById(planetCreate.getId());

        // Then
        assertNotNull(getPlanet);
        assertEquals(planetID, getPlanet.getId());


    }

    @Test
    void testGetById() {
        // Given
        Planet planet1 = planet;

        // When
        Planet getPlanet = planetCrudService.getById(planetID);

        // Then
        assertNotNull(getPlanet);
        assertEquals(planet1.getName(), getPlanet.getName());
        assertEquals(planet1.getId(), getPlanet.getId());

    }

    @Test
    public void testUpdate() {
        // Given

        // When
        Planet planetToUpdate = planetCrudService.getById(planetID);
        planetToUpdate.setName("SATURN");
        planetCrudService.update(planetToUpdate);
        Planet updatedPlanet = planetCrudService.getById(planetID);

        // Then
        assertNotNull(updatedPlanet);
        assertEquals("SATURN", updatedPlanet.getName());

    }

    @Test
    public void testDelete(){
        // Given

        // When
        planetCrudService.delete(planetID);
        Planet getPlanet = planetCrudService.getById(planetID);

        // Then
        assertNull(getPlanet);
    }
}
