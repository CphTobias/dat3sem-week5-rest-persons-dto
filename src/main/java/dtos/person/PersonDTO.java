package dtos.person;

import entities.person.Person;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public static List<PersonDTO> getFromPersonList(List<Person> people) {
        return people.stream()
            .map(person -> new PersonDTO(person))
            .collect(Collectors.toList());
    }

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.id = person.getPersonId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phoneNumber = person.getPhoneNumber();
    }

    public PersonDTO(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonDTO)) {
            return false;
        }
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(id, personDTO.id) && Objects
            .equals(getFirstName(), personDTO.getFirstName()) && Objects
            .equals(getLastName(), personDTO.getLastName()) && Objects
            .equals(getPhoneNumber(), personDTO.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getFirstName(), getLastName(), getPhoneNumber());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
