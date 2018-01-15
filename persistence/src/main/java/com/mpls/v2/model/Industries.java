package com.mpls.v2.model;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Table
public class Industries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    private String image;

    @OneToMany(mappedBy = "industries")
    private List<Blog> blogsList;

    public long getId() {
        return id;
    }

    public Industries setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Industries setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Industries setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Industries setImage(String image) {
        this.image = image;
        return this;
    }

    public List<Blog> getBlogsList() {
        return blogsList;
    }

    public Industries setBlogsList(List<Blog> blogsList) {
        this.blogsList = blogsList;
        return this;
    }

    @Override
    public String toString() {
        return "Industries{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", blogsList=" + blogsList.stream().map(Blog::getHeader).collect(toList()) +
                '}';
    }
}
