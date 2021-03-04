package dtos.person;

import entities.person.Person;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonsDTO {

    private List<PersonDTO> all;

    public PersonsDTO(List<Person> people) {
        this.all = PersonDTO.getFromPersonList(people);
    }

    public List<PersonDTO> getAll() {
        return all;
    }

    public void setAll(List<PersonDTO> all) {
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
}

