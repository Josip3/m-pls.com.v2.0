package com.mpls.v2.dto;

import com.mpls.v2.model.Group;

public class UserFullDTO extends UserShortDTO {

    Group team;

    public Group getTeam() {
        return team;
    }

    public UserFullDTO setTeam(Group team) {
        this.team = team;
        return this;
    }

    @Override
    public String toString() {
        return "UserFullDTO{" +
                "team=" + team.getName() +
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
