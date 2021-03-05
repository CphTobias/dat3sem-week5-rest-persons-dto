package dtos;


import entities.address.Address;

public class AddressDTO {

    private Integer id;
    private String street;
    private String zip;
    private String city;

    public AddressDTO(Address address) {
        this.id = address.getAddressId();
        this.street = address.getStreet();
        this.zip = address.getZip();
        this.city = address.getCity();
    }

    public AddressDTO() {
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
            "id=" + id +
            ", street='" + street + '\'' +
            ", zip='" + zip + '\'' +
            ", city='" + city + '\'' +
            '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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