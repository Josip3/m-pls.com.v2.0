package com.mpls.v2.dto;

import com.mpls.v2.model.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GroupFullDTO extends GroupShortDTO {

    List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public GroupFullDTO setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    @Override
    public String toString() {
        return "GroupFullDTO{" +
                "users=" + users.stream().map(User::getUsername).collect(toList()) +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
