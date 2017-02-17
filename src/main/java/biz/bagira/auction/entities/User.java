package biz.bagira.auction.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy on 23.01.2017.
 */
@Entity
@Table(name = "USERS")
public class User {
    private Integer idUsers;
    private String login;
    private String password;
    //need to delete
    private byte[] pswSalt;

    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private byte validateEmail;
    private String picture;
    private List<Item> itemList = new ArrayList<Item>();
    private List<Bid> bidList = new ArrayList<Bid>();
    private List<Order> orderList = new ArrayList<Order>();
    private String state=State.ACTIVE.getState();
    private List<UserProfile> userProfiles = new ArrayList<UserProfile>();

    @Id
    @Column(name = "ID_USERS")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Integer idUsers) {
        this.idUsers = idUsers;
    }

    @Column(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "PSW_SALT")
    public byte[] getPswSalt() {
        return pswSalt;
    }

    public void setPswSalt(byte[] pswSalt) {
        this.pswSalt = pswSalt;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(name = "VALIDATE_EMAIL")
    public byte getValidateEmail() {
        return validateEmail;
    }

    public void setValidateEmail(byte validateEmail) {
        this.validateEmail = validateEmail;
    }

    @Column(name = "PICTURE")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "owner")
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "userBidder")
    public List<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(List<Bid> bidList) {
        this.bidList = bidList;
    }



    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "winner")
    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Column(name="STATE", nullable=false)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "USERS_PROFILE", joinColumns = @JoinColumn(name = "ID_USERS"), inverseJoinColumns = @JoinColumn(name = "ID_PROFILE"))

    public List<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(List<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }

    public void addUserProfile(UserProfile profile)
    {
        userProfiles.add(profile);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (idUsers != null ? !idUsers.equals(user.idUsers) : user.idUsers != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;

    }

    @Override
    public int hashCode() {
        int result = idUsers != null ? idUsers.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUsers=" + idUsers +
                '}';
    }
}
