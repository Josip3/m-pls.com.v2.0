package com.mpls.v2.service.impl;

import com.mpls.v2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by danul on 18.10.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User save(User user) {
        return userRepository.save(user.setPassword(encoder.encode(user.getPassword())));
    }
}
