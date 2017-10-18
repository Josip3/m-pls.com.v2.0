package com.mpls.v2.repository;

import com.mpls.v2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by danul on 18.10.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

}
