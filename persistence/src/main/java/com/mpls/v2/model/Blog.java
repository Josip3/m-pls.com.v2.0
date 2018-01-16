package com.mpls.v2.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.v2.utils.deserialization.DateDeserializer;
import com.mpls.v2.utils.serializer.DateSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String header;

    private String mainText;

    private String image;

    private LocalDateTime date;

    private Boolean available;

    @ManyToOne
    private Industries industries;

    public Long getId() {
        return id;
    }

    public Blog setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Blog setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getHeader() {
        return header;
    }

    public Blog setHeader(String header) {
        this.header = header;
        return this;
    }

    public String getMainText() {
        return mainText;
    }

    public Blog setMainText(String mainText) {
        this.mainText = mainText;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Blog setImage(String image) {
        this.image = image;
        return this;
    }

    @JsonSerialize(using = DateSerializer.class)
    public LocalDateTime getDate() {
        return date;
    }

    @JsonDeserialize(using = DateDeserializer.class)
    public Blog setDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public Industries getIndustries() {
        return industries;
    }

    public Blog setIndustries(Industries industries) {
        this.industries = industries;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Blog setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", header='" + header + '\'' +
                ", mainText='" + mainText + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", industries=" + industries.getName() +
                '}';
    }
}
