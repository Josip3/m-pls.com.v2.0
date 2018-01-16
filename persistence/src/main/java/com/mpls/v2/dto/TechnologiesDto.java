package com.mpls.v2.dto;

public class TechnologiesDto {

    private Long id;

    private String name;

    private String description;

    private Boolean available;

    private String image;

    public Boolean getAvailable() {
        return available;
    }

    public TechnologiesDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public Long getId() {
        return id;
    }

    public TechnologiesDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TechnologiesDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TechnologiesDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public TechnologiesDto setImage(String image) {
        this.image = image;
        return this;
    }

    @Override
    public String toString() {
        return "TechnologiesDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
