package com.mpls.v2.dto;

import com.mpls.v2.model.Group;

public class UserFullDTO extends UserShortDTO {

    Group group;

    public Group getGroup() {
        return group;
    }

    public UserFullDTO setGroup(Group group) {
        this.group = group;
        return this;
    }

    @Override
    public String toString() {
        return "UserFullDTO{" +
                "group=" + group +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", post='" + post + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", registrationDate=" + registrationDate +
                ", role=" + role +
                '}';
    }
}
