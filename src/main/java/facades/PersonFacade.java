package facades;

import dtos.person.PersonDTO;
import dtos.person.PersonsDTO;
import entities.address.Address;
import entities.person.Person;
import entities.person.PersonRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.ws.rs.WebApplicationException;

public class PersonFacade implements PersonRepository {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    private PersonFacade() {
    }

    public static PersonFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    @Override
    public PersonDTO addPerson(PersonDTO personDTO) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Person person = new Person(personDTO.getFirstName(), personDTO.getLastName(),
            personDTO.getPhoneNumber());

        if (person.getFirstName() == null || person.getLastName() == null) {
            throw new WebApplicationException("Unable to create person: Missing firstName or lastName", 400);
        }

        try {
            Address address = em.createQuery(
                "SELECT a FROM Address a WHERE a.city = :city AND a.street = :street AND a.zip = :zip",
                Address.class
            ).setParameter("city", personDTO.getAddress().getCity())
                .setParameter("street", personDTO.getAddress().getStreet())
                .setParameter("zip", personDTO.getAddress().getZip())
                .getSingleResult();
            person.setAddress(address);
        } catch (NoResultException e) {
            person.setAddress(new Address(personDTO.getAddress()));
        }

        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    @Override
    public PersonDTO deletePerson(int id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null) {
            throw new WebApplicationException("Person with id: " + id + " was not found", 404);
        }
        PersonDTO personDTO = new PersonDTO(person);

        try {
            em.getTransaction().begin();
            person.getAddress().getPeople().remove(person);
            em.remove(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return personDTO;
    }

    @Override
    public PersonDTO getPersonById(int id) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            Person person = em.find(Person.class, id);
            if (person == null) {
                throw new WebApplicationException("Person with id: " + id + " was not found");
            }
            return new PersonDTO(person);
        } finally {
            em.close();
        }
    }

    @Override
    public PersonsDTO getAllPersons() throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            List<Person> people = em.createQuery("SELECT p FROM Person p", Person.class)
                .getResultList();
            if (people == null) {
                throw new WebApplicationException("No people were found", 404);
            }
            return new PersonsDTO(people);
        } finally {
            em.close();
        }
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) throws WebApplicationException {
        EntityManager em = emf.createEntityManager();

        if (p.getFirstName() == null || p.getLastName() == null) {
            throw new WebApplicationException("Unable to update person: Missing firstName or lastName", 400);
        }

        Person person = em.find(Person.class, p.getId());
        if (person == null) {
            throw new WebApplicationException("Person with id: " + p.getId() + " was not found", 404);
        }

        try {
            em.getTransaction().begin();
            person.updatePerson(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return new PersonDTO(person);
    }
}
