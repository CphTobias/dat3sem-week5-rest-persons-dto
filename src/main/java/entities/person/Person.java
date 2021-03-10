package entities.person;

import dtos.person.PersonDTO;
import entities.address.Address;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "person")
@NamedQuery(name = "Person.deleteAllRows", query = "DELETE from Person")
public class Person implements Serializable {

    private static final long serialVersionUID = -5221838779790920829L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personId")
    private Integer personId;

    @Column(
        name = "firstName",
        nullable = false,
        length = 30
    )
    private String firstName;

    @Column(
        name = "lastName",
        nullable = false,
        length = 30
    )
    private String lastName;

    @Column(
        name = "phoneNumber",
        nullable = false,
        length = 30
    )
    private String phoneNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "createdAt")
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "lastEdited")
    private Date lastEdited;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;

    public Person() {
    }

    public Person(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.createdAt = new Date();
        this.lastEdited = new Date();
    }

    public void updatePerson(PersonDTO person) {
        if (
            //Is it a new address and does it contain more than one person in it
            this.getAddress().isNewAddress(person.getAddress()) && this.getAddress().getPeople().size() > 1
        ) {
            this.setAddress(new Address(person.getAddress()));
        } else {
            //if it is a new address but only contains that one person, then we update it instead of creating a new one
            this.getAddress().updateAddress(person.getAddress());
        }
        this.setFirstName(person.getFirstName());
        this.setLastName(person.getLastName());
        this.setPhoneNumber(person.getPhoneNumber());
    }

    public void setAddress(Address address) {
        if (address != null) {
            this.address = address;
            address.addPerson(this);
        }
    }


    public void setPersonId(Integer id) {
        this.personId = id;
    }

    public Integer getPersonId() {
        return personId;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
            "personId=" + personId +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", createdAt=" + createdAt +
            ", lastEdited=" + lastEdited +
            ", address=" + address +
            '}';
    }
}
