package entities.address;

import dtos.AddressDTO;
import entities.person.Person;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = -876832103313757569L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressId")
    private Integer addressId;

    @Column(
        name = "street",
        length = 50,
        nullable = false
    )
    private String street;

    @Column(
        name = "zip",
        length = 30,
        nullable = false
    )
    private String zip;

    @Column(
        name = "city",
        length = 50,
        nullable = false
    )
    private String city;

    @OneToMany(mappedBy = "address")
    private List<Person> people;

    public Address() {
    }

    public Address(String street, String zip, String city) {
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.people = new ArrayList<>();
    }

    public Address(AddressDTO addressDTO) {
        this.street = addressDTO.getStreet();
        this.zip = addressDTO.getZip();
        this.city = addressDTO.getCity();
        this.people = new ArrayList<>();
    }

    public boolean isNewAddress(AddressDTO address) {
        if (!this.city.equals(address.getCity())) {
            return true;
        } else if (!this.street.equals(address.getStreet())) {
            return true;
        } else if (!this.zip.equals(address.getZip())) {
            return true;
        }
        return false;
    }

    public void updateAddress(AddressDTO address) {
        if (this.people.size() < 2) {
            this.setStreet(address.getStreet());
            this.setZip(address.getZip());
            this.setCity(address.getCity());
        }
    }

    public void addPerson(Person person) {
        if (person != null) {
            this.people.add(person);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals(getAddressId(), address.getAddressId()) && Objects
            .equals(getStreet(), address.getStreet()) && Objects.equals(getZip(), address.getZip())
            && Objects.equals(getCity(), address.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAddressId(), getStreet(), getZip(), getCity());
    }

    @Override
    public String toString() {
        return "Address{" +
            "addressId=" + addressId +
            ", street='" + street + '\'' +
            ", zip='" + zip + '\'' +
            ", city='" + city + '\'' +
            ", people=" + people +
            '}';
    }

    public List<Person> getPeople() {
        return people;
    }


    public void setAddressId(Integer id) {
        this.addressId = id;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
