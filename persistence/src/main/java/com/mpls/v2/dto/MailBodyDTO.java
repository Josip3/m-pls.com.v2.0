package com.mpls.v2.dto;

public class MailBodyDTO {

    private Long id;

    private String name;

    private String path;

    public MailBodyDTO() {
    }

    public MailBodyDTO(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public MailBodyDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MailBodyDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public MailBodyDTO setPath(String path) {
        this.path = path;
        return this;
    }

    @Override
    public String toString() {
        return "MailBodyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
