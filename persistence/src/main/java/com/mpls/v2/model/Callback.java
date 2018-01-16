package com.mpls.v2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Callback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String info;

    private Boolean available = false;

    public Callback() {
    }

    public Boolean getAvailable() {
        return available;
    }

    public Callback setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Callback setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Callback setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Callback setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public Callback setInfo(String info) {
        this.info = info;
        return this;
    }

    @Override
    public String toString() {
        return "Callback{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
