package dtos.person;

import entities.person.Person;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonDTO {

    private String firstName;
    private String lastName;
    private String phoneNumber;

    public static List<PersonDTO> getFromPersonList(List<Person> people) {
        return people.stream()
            .map(person -> new PersonDTO(person))
            .collect(Collectors.toList());
    }

    public PersonDTO(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public PersonDTO(Person person) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.phoneNumber = person.getPhoneNumber();
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
            "firstName='" + firstName + '\'' +
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
        return Objects.equals(getFirstName(), personDTO.getFirstName()) && Objects
            .equals(getLastName(), personDTO.getLastName()) && Objects
            .equals(getPhoneNumber(), personDTO.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getPhoneNumber());
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
