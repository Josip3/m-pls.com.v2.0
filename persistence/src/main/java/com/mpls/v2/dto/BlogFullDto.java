package com.mpls.v2.dto;

public class BlogFullDto extends BlogDto {

    private IndustriesShortDto event;

    public IndustriesShortDto getEvent() {
        return event;
    }

    public BlogFullDto setEvent(IndustriesShortDto event) {
        this.event = event;
        return this;
    }


    @Override
    public String toString() {
        return "BlogFullDto{" +
                "event=" + event +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", header='" + header + '\'' +
                ", mainText='" + mainText + '\'' +
                ", image='" + image + '\'' +
                ", date=" + date +
                '}';
    }
}
