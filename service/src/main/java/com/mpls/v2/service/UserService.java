package com.mpls.v2.service;

import com.mpls.v2.model.User;

import java.util.List;

public interface UserService {

    User save(User user);//post

    List<User> findAll();//get

    User findOne(Long id);//get

    Boolean delete(Long id);//delete

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    User findByUsername(String username);

    User findByEmail(String email);

}
