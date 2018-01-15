package com.mpls.v2.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String shortDescr;

    private String header;

    private String mainText;

    private String image;

    private LocalDateTime date;

    @OneToMany
    private Industries industries;

    public long getId() {
        return id;
    }

    public Blog setId(long id) {
        this.id = id;
        return this;
    }

    public String getShortDescr() {
        return shortDescr;
    }

    public Blog setShortDescr(String shortDescr) {
        this.shortDescr = shortDescr;
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

    public LocalDateTime getDate() {
        return date;
    }

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

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", shortDescr='" + shortDescr + '\'' +
                ", header='" + header + '\'' +
                ", mainText='" + mainText + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", industries=" + industries.getName() +
                '}';
    }
}
