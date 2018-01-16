package com.mpls.v2.dto;

import java.util.List;

public class IndustriesFullDto extends IndustriesShortDto {

    List<BlogShortDto> blogsList;

    public List<BlogShortDto> getBlogsList() {
        return blogsList;
    }

    public IndustriesFullDto setBlogsList(List<BlogShortDto> blogsList) {
        this.blogsList = blogsList;
        return this;
    }

    @Override
    public String toString() {
        return "IndustriesFullDto{" +
                "blogsList=" + blogsList +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
