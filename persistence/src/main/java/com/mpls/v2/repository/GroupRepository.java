package com.mpls.v2.repository;

import com.mpls.v2.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {

    Group findByName(String name);

}
