package com.mpls.v2.dto;

public class IndustriesShortDto {

    protected Long id;

    protected String name;

    protected String description;

    protected String image;

    protected Boolean available;

    public Boolean getAvailable() {
        return available;
    }

    public IndustriesShortDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public Long getId() {
        return id;
    }

    public IndustriesShortDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public IndustriesShortDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public IndustriesShortDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public IndustriesShortDto setImage(String image) {
        this.image = image;
        return this;
    }

    @Override
    public String toString() {
        return "IndustriesShortDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
