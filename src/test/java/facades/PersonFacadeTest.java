package facades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dtos.person.PersonDTO;
import dtos.person.PersonsDTO;
import entities.person.Person;
import entities.person.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

public class PersonFacadeTest {

    private static EntityManagerFactory emf;
    private static PersonRepository repo;

    private Person person1;
    private Person person2;
    private Person person3;

    public PersonFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        repo = PersonFacade.getInstance(emf);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        person1 = new Person("First1", "First2", "1111");
        person2 = new Person("Second1", "Second2", "2222");
        person3 = new Person("Third1", "Third2", "3333");
        try {
            em.getTransaction().begin();
            em.persist(person1);
            em.persist(person2);
            em.persist(person3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Person.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testGetPersonById() {
        PersonDTO actual = null;
        try {
            actual = repo.getPersonById(person1.getPersonId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        PersonDTO expected = new PersonDTO(person1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAllPersons() {
        PersonsDTO actual = null;
        try {
            actual = repo.getAllPersons();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);
        people.add(person3);
        PersonsDTO expected = new PersonsDTO(people);

        assertEquals(expected.getAll().toArray().length, actual.getAll().toArray().length);
    }

    @Test
    public void testAddPerson() {
        PersonDTO personDTO = new PersonDTO("Test", "Name", "4321");
        PersonDTO actual = null;
        try {
            actual = repo.addPerson(personDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("Test", actual.getFirstName());
        assertEquals("Name", actual.getLastName());
        assertEquals("4321", actual.getPhoneNumber());
        assertNotNull(actual.getId());
    }

    @Test
    public void testEditPerson() {
        Person oldPerson = person1;
        PersonDTO toBe = new PersonDTO("Edit", "Ted", "1234");
        toBe.setId(person1.getPersonId());

        PersonDTO actual = null;
        try {
            actual = repo.editPerson(toBe);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("Edit", actual.getFirstName());
        assertEquals("Ted", actual.getLastName());
        assertEquals("1234", actual.getPhoneNumber());

        assertNotEquals(actual, new PersonDTO(oldPerson));
    }

    @Test
    public void testDeletePerson() {
        PersonDTO deletedPerson = null;
        try {
            deletedPerson = repo.deletePerson(person1.getPersonId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(deletedPerson);
    }
}
