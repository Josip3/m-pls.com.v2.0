package com.mpls.v2.service.impl;

import com.mpls.v2.model.User;
import com.mpls.v2.repository.UserRepository;
import com.mpls.v2.service.UserService;
import com.mpls.v2.service.exceptions.IdException;
import com.mpls.v2.service.exceptions.SaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {

        if (user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }else{
            throw new SaveException("User must be not null");
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        if (id != null || id >= 0) {
            return userRepository.findOne(id);
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            userRepository.delete(userRepository.findOne(id));
            return true;
        } else {
            throw new IdException("id must be not null");
        }
    }


    @Override
    public User findByFirstName(String firstName) {
        if (firstName != null) {
            return userRepository.findByFirstName(firstName);
        } else {
            throw new SaveException("firstName must be not null");
        }
    }

    @Override
    public User findByLastName(String lastName) {
        if (lastName != null) {
            return userRepository.findByLastName(lastName);
        } else {
            throw new SaveException("lastName must be not null");
        }
    }

    @Override
    public User findByUsername(String username) {
        if (username != null) {
            return userRepository.findByUsername(username);
        } else {
            throw new SaveException("userName must be not null");
        }
    }

    @Override
    public User findByEmail(String email) {
        if (email != null) {
            return userRepository.findByEmail(email);
        } else {
            throw new SaveException("email must be not null");
        }
    }
}
