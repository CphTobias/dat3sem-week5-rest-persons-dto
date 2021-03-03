package entities.person;

import dtos.person.PersonDTO;
import dtos.person.PersonsDTO;

public interface PersonRepository {

    public PersonDTO addPerson(String fName, String lName, String phone);
    public PersonDTO deletePerson(long id);
    public PersonDTO getPersonById(long id);
    public PersonsDTO getAllPersons();
    public PersonDTO editPerson(PersonDTO p);


}
