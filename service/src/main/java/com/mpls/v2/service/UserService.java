package com.mpls.v2.service;

import com.mpls.v2.dto.UserFullDTO;
import com.mpls.v2.dto.UserShortDTO;
import com.mpls.v2.model.User;

import java.util.List;

public interface UserService {

    User save(User user);//post

    User update(User user);

    User update(UserShortDTO userShortDTO);

    User update(UserFullDTO userFullDTO);

    List<User> findAll();//get

    User findOne(Long id);//get

    Boolean delete(Long id);//delete

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    User findByUsername(String username);

    User findByEmail(String email);

}
