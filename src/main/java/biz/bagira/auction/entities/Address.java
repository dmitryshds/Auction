package biz.bagira.auction.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * Created by Dmitriy on 23.01.2017.
 */
@Entity
@Table(name = "ADDRESS")
public class Address {
    private Integer userId;
    private String country;
    private String city;
    private String street;
    private String homeNumber;
    private User user;


    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @Parameter(name = "property", value = "user"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "USER_ID", unique = true, nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "HOME_NUMBER")
    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (userId != null ? !userId.equals(address.userId) : address.userId != null) return false;
        return country != null ? country.equals(address.country) : address.country == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                '}';
    }
}
