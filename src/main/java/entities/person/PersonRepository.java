package entities.person;

import dtos.person.PersonDTO;
import dtos.person.PersonsDTO;

public interface PersonRepository {

    public PersonDTO addPerson(PersonDTO person);
    public PersonDTO deletePerson(int id);
    public PersonDTO getPersonById(int id);
    public PersonsDTO getAllPersons();
    public PersonDTO editPerson(PersonDTO p);


}
