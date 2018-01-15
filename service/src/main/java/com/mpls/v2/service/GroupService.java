package com.mpls.v2.service;

import com.mpls.v2.model.Group;

import java.util.List;

public interface GroupService {

    Group save (Group group);

    List<Group> findAll();

    Group findOne(Long id);

    Boolean delete(Long id);

    Group findByName(String name);
}
