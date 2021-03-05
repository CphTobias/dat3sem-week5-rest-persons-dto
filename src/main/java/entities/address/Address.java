package entities.address;

import dtos.AddressDTO;
import entities.person.Person;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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

    @OneToOne(mappedBy = "address")
    private Person person;

    public Address() {
    }

    public Address(String street, String zip, String city) {
        this.street = street;
        this.zip = zip;
        this.city = city;
    }

    public Address(AddressDTO addressDTO) {
        this.street = addressDTO.getStreet();
        this.zip = addressDTO.getZip();
        this.city = addressDTO.getCity();
    }

    public void updateAddress(AddressDTO address) {
        this.setStreet(address.getStreet());
        this.setZip(address.getZip());
        this.setCity(address.getCity());
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
            "id=" + addressId +
            ", street='" + street + '\'' +
            ", zip='" + zip + '\'' +
            ", city='" + city + '\'' +
            '}';
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
