package com.mpls.v2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MailBody {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String path;

    private Boolean available;

    public MailBody() {
    }

    public Boolean getAvailable() {
        return available;
    }

    public MailBody setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public Long getId() {
        return id;
    }

    public MailBody setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MailBody setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public MailBody setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toString() {
        return "MailBody{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
