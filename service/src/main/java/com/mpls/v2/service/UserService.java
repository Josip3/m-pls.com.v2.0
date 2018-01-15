package com.mpls.v2.service;

import com.mpls.v2.model.User;

import java.util.List;

/**
 * Created by danul on 18.10.2017.
 */
public interface UserService {

    User save(User user);

    List<User> findAll();

    User findOne(Long id);

    Boolean delete(Long id);

    User findByFirstName(String firstName);

    User findByLastName(String lastName);

    User findByUsername(String username);

    User findByEmail(String email);

}
