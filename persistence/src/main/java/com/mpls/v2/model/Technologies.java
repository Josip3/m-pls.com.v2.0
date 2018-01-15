package com.mpls.v2.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
public class Technologies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private String image;

    public long getId() {
        return id;
    }

    public Technologies setId(long id) {
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
