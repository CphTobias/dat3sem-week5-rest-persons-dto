package entities.person;

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
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "lastEdited")
    private Date lastEdited;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressId")
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

    public void setAddress(Address address) {
        if (address != null) {
            this.address = address;
            address.setPerson(this);
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
