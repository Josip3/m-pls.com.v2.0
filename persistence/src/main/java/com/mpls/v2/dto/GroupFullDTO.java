package com.mpls.v2.dto;

import com.mpls.v2.model.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class GroupFullDTO extends GroupShortDTO {

    List<UserShortDTO> users;

    public List<UserShortDTO> getUsers() {
        return users;
    }

    public GroupFullDTO setUsers(List<UserShortDTO> users) {
        this.users = users;
        return this;
    }

    @Override
    public String toString() {
        return "GroupFullDTO{" +
                "users=" + users.stream().map(UserShortDTO::getUsername).collect(toList()) +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
