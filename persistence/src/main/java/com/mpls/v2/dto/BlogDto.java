package com.mpls.v2.dto;


import java.time.LocalDateTime;

public class BlogDto {

    protected Long id;

    protected String description;

    protected String header;

    protected String mainText;

    protected String image;

    protected LocalDateTime date;

    public Long getId() {
        return id;
    }

    public BlogDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BlogDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getHeader() {
        return header;
    }

    public BlogDto setHeader(String header) {
        this.header = header;
        return this;
    }

    public String getMainText() {
        return mainText;
    }

    public BlogDto setMainText(String mainText) {
        this.mainText = mainText;
        return this;
    }

    public String getImage() {
        return image;
    }

    public BlogDto setImage(String image) {
        this.image = image;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public BlogDto setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "BlogDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", header='" + header + '\'' +
                ", mainText='" + mainText + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                '}';
    }
}
