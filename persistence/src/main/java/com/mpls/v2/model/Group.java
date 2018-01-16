package com.mpls.v2.model;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "team")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String image;

    @OneToMany(mappedBy = "team")
    private List<User> users;

    public Group() {
    }

    public Group(String name, String description, String image, List<User> users) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.users = users;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Group setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Group setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Group setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Group setImage(String image) {
        this.image = image;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public Group setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", users=" + users.stream().map(User::getUsername).collect(toList()) +
                '}';
    }
}
