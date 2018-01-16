package com.mpls.v2.service;

import com.mpls.v2.dto.GroupFullDTO;
import com.mpls.v2.dto.GroupShortDTO;
import com.mpls.v2.model.Group;

import java.util.List;

public interface GroupService {

    Group save (Group group);

    Group update(Group group);

    Group update(GroupShortDTO groupShortDTO);

    Group update(GroupFullDTO groupFullDTO);

    List<Group> findAll();

    Group findOne(Long id);

    Boolean delete(Long id);

    Group findByName(String name);
}
