package com.mpls.v2.dto;

public class BlogFullDto extends BlogDto {

    private IndustriesShortDto industries;

    public IndustriesShortDto getIndustries() {
        return industries;
    }

    public BlogFullDto setIndustries(IndustriesShortDto industries) {
        this.industries = industries;
        return this;
    }

    @Override
    public String toString() {
        return "BlogFullDto{" +
                "industries=" + industries +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", header='" + header + '\'' +
                ", mainText='" + mainText + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                '}';
    }
}
