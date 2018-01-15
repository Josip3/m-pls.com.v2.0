package com.mpls.v2.repository;

import com.mpls.v2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByFirstName(String firstName);

    User findByLastName(String lastName);

    User findByUsername(String username);

    User findByEmail(String email);

}
