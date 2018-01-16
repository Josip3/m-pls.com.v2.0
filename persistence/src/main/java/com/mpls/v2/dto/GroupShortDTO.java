package com.mpls.v2.dto;

public class GroupShortDTO {

    protected Long id;

    protected String name;

    protected String description;

    protected String image;

    protected Boolean available;

    public GroupShortDTO() {
    }

    public GroupShortDTO(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Boolean getAvailable() {
        return available;
    }

    public GroupShortDTO setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public Long getId() {
        return id;
    }

    public GroupShortDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GroupShortDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public GroupShortDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public GroupShortDTO setImage(String image) {
        this.image = image;
        return this;
    }

    @Override
    public String toString() {
        return "GroupShortDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
