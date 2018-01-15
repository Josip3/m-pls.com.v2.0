package com.mpls.v2.service;

import com.mpls.v2.model.User;

import java.util.List;

/**
 * Created by danul on 18.10.2017.
 */
public interface UserService {

    User save(User user);//post

    List<User> findAll();//get

    User findOne(Long id);//get

    Boolean delete(Long id);//delete

    User findByFirstName(String firstName);//get----

    User findByLastName(String lastName);//---

    User findByUsername(String username);

    User findByEmail(String email);

}
