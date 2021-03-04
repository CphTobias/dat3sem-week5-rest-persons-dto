package entities.person;

import dtos.person.PersonDTO;
import dtos.person.PersonsDTO;
import javax.ws.rs.WebApplicationException;

public interface PersonRepository {

    public PersonDTO addPerson(PersonDTO person) throws WebApplicationException;

    public PersonDTO deletePerson(int id) throws WebApplicationException;

    public PersonDTO getPersonById(int id) throws WebApplicationException;

    public PersonsDTO getAllPersons() throws WebApplicationException;

    public PersonDTO editPerson(PersonDTO p) throws WebApplicationException;


}
