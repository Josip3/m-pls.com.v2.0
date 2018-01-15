package com.mpls.v2.dto;

public class CallbackDTO {

    private Long id;

    private String name;

    private String email;

    private String info;

    public CallbackDTO() {
    }

    public CallbackDTO(String name, String email, String info) {
        this.name = name;
        this.email = email;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public CallbackDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CallbackDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CallbackDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public CallbackDTO setInfo(String info) {
        this.info = info;
        return this;
    }

    @Override
    public String toString() {
        return "CallbackDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
