package com.mpls.v2.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.v2.enums.Roles;
import com.mpls.v2.utils.deserialization.DateDeserializer;
import com.mpls.v2.utils.deserialization.RoleDeserializer;
import com.mpls.v2.utils.serializer.DateSerializer;
import com.mpls.v2.utils.serializer.RolesSerializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String image;
    private String post;
    private String description;
    private String phone;
    private String address;
    private String city;
    private String email;
    private String username;
    private LocalDateTime registrationDate;
    private Boolean available;
    private String uuid;
    private Roles role;
    @ManyToOne
    private Group team;


    public User() {
    }

    public User(String firstName, String lastName, String image, String post, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
        this.post = post;
        this.description = description;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        return authorities;
    }

    public Group getTeam() {
        return team;
    }

    public User setTeam(Group team) {
        this.team = team;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public User setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }


    public User setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public boolean isAccountNonExpired() {
        return available;
    }

    @Override
    public boolean isAccountNonLocked() {
        return available;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return available;
    }

    @Override
    public boolean isEnabled() {
        return available;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getImage() {
        return image;
    }

    public User setImage(String image) {
        this.image = image;
        return this;
    }

    public String getPost() {
        return post;
    }

    public User setPost(String post) {
        this.post = post;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

    public Group getGroup() {
        return team;
    }

    public User setGroup(Group team) {
        this.team = team;
        return this;
    }

    @JsonSerialize(using = RolesSerializer.class)
    public Roles getRole() {
        return role;
    }

    @JsonDeserialize(using = RoleDeserializer.class)
    public User setRole(Roles role) {
        this.role = role;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public User setCity(String city) {
        this.city = city;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public User setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", image='" + image + '\'' +
                ", post='" + post + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", registrationDate=" + registrationDate +
                ", team=" + team.getName() +
                ", role=" + role.name().toUpperCase() +
                '}';
    }
}