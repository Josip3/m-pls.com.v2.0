package com.mpls.v2.dto;

import com.mpls.v2.enums.Roles;

import java.time.LocalDateTime;

public class UserShortDTO {

    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String password;
    protected String image;
    protected String post;
    protected String description;
    protected String phone;
    protected String address;
    protected String city;
    protected String email;
    protected String username;
    protected LocalDateTime registrationDate;
    protected Roles role;
    protected Boolean available;

    public Boolean getAvailable() {
        return available;
    }

    public UserShortDTO setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserShortDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserShortDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserShortDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserShortDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getImage() {
        return image;
    }

    public UserShortDTO setImage(String image) {
        this.image = image;
        return this;
    }

    public String getPost() {
        return post;
    }

    public UserShortDTO setPost(String post) {
        this.post = post;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserShortDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserShortDTO setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public UserShortDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public UserShortDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserShortDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserShortDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public UserShortDTO setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    public Roles getRole() {
        return role;
    }

    public UserShortDTO setRole(Roles role) {
        this.role = role;
        return this;
    }

    @Override
    public String toString() {
        return "UserShortDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", post='" + post + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", registrationDate=" + registrationDate +
                ", role=" + role +
                '}';
    }
}
