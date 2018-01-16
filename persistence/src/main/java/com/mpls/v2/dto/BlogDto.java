package com.mpls.v2.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.v2.utils.deserialization.DateDeserializer;
import com.mpls.v2.utils.serializer.DateSerializer;

import java.time.LocalDateTime;

public class BlogDto {

    protected Long id;

    protected String description;

    protected String header;

    protected String mainText;

    protected String image;

    protected LocalDateTime date;

    protected Boolean available;

    public Boolean getAvailable() {
        return available;
    }

    public BlogDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

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

    @JsonSerialize(using = DateSerializer.class)
    public LocalDateTime getDate() {
        return date;
    }

    @JsonDeserialize(using = DateDeserializer.class)
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
