package facades;

import dtos.person.PersonDTO;
import dtos.person.PersonsDTO;
import entities.person.Person;
import entities.person.PersonRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        if (person.getPersonId() == null) {
            throw new WebApplicationException("Unable to create person, try again!", 409);
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
                throw new WebApplicationException("Person with id: " + id + " was not found", 404);
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
        Person person = em.find(Person.class, p.getId());
        if (person == null) {
            throw new WebApplicationException("Person with id: " + p.getId() + " was not found", 404);
        }

        try {
            em.getTransaction().begin();
            person.setFirstName(p.getFirstName());
            person.setLastName(p.getLastName());
            person.setPhoneNumber(p.getPhoneNumber());
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return new PersonDTO(person);
    }
}
