package com.mpls.v2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;
    private String originalName;

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public Image setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Image setPath(String path) {
        this.path = path;
        return this;
    }

    public String getOriginalName() {
        return originalName;
    }

    public Image setOriginalName(String originalName) {
        this.originalName = originalName;
        return this;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", originalName='" + originalName + '\'' +
                '}';
    }
}
