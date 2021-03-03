package dtos.person;

import entities.person.Person;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonsDTO {

    private List<InnerPersonDTO> all;

    public PersonsDTO(List<Person> people) {
        this.all = people.stream()
            .map(person -> new InnerPersonDTO(person))
            .collect(Collectors.toList());
    }

    public List<InnerPersonDTO> getAll() {
        return all;
    }

    public void setAll(List<InnerPersonDTO> all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "PersonsDTO{" +
            "all=" + all +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonsDTO)) {
            return false;
        }
        PersonsDTO that = (PersonsDTO) o;
        return Objects.equals(getAll(), that.getAll());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAll());
    }

    private static class InnerPersonDTO {

        private Long id;
        private String firstName;
        private String lastName;
        private String phoneNumber;

        public InnerPersonDTO(Person person) {
            this.id = person.getPersonId();
            this.firstName = person.getFirstName();
            this.lastName = person.getLastName();
            this.phoneNumber = person.getPhoneNumber();
        }

        @Override
        public String toString() {
            return "InnerPersonDTO{" +
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
            if (!(o instanceof InnerPersonDTO)) {
                return false;
            }
            InnerPersonDTO that = (InnerPersonDTO) o;
            return Objects.equals(getId(), that.getId()) && Objects
                .equals(getFirstName(), that.getFirstName()) && Objects
                .equals(getLastName(), that.getLastName()) && Objects
                .equals(getPhoneNumber(), that.getPhoneNumber());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getFirstName(), getLastName(), getPhoneNumber());
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
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
}

