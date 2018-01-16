package com.mpls.v2.model;

import javax.persistence.*;

@Entity
public class Technologies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    private Boolean available = false;

    private String image;

    public Boolean getAvailable() {
        return available;
    }

    public Technologies setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Technologies setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Technologies setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Technologies setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Technologies setImage(String image) {
        this.image = image;
        return this;
    }

    @Override
    public String toString() {
        return "Technologies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
