package com.mpls.v2.repository;

import com.mpls.v2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByFirstName(String firstName);

    User findByLastName(String lastName);

}
