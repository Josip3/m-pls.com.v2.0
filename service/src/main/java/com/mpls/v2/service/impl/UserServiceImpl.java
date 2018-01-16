package com.mpls.v2.service.impl;

import com.mpls.v2.dto.UserFullDTO;
import com.mpls.v2.dto.UserShortDTO;
import com.mpls.v2.enums.Roles;
import com.mpls.v2.model.User;
import com.mpls.v2.repository.UserRepository;
import com.mpls.v2.service.UserService;
import com.mpls.v2.service.exceptions.FindException;
import com.mpls.v2.service.exceptions.IdException;
import com.mpls.v2.service.exceptions.SaveException;
import com.mpls.v2.service.exceptions.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.mpls.v2.dto.utils.builder.Builder.map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        if (user != null) {
            return userRepository.save(user
                    .setPassword(passwordEncoder.encode(user.getPassword()))
                    .setUuid(UUID.randomUUID().toString())
                    .setRole(Roles.DEFAULT)
                    .setRegistrationDate(LocalDateTime.now())
                    .setAvailable(true));
        } else {
            throw new SaveException("User must be not null");
        }
    }


    @Override
    public User update(User user) {
        if (user.getId() == null || user.getId() < 1)
            throw new UpdateException(" invalid id UserService");
        else if (userRepository.findOne(user.getId()) == null)
            throw new UpdateException(" there are no user with such id ");
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UpdateException("UserService");
        }
    }

    @Override
    public User update(UserShortDTO userShortDTO) {
        User user;
        if (userShortDTO.getId() == null || userShortDTO.getId() < 1)
            throw new UpdateException(" invalid id UserService");
        else if ((user = userRepository.findOne(userShortDTO.getId())) == null)
            throw new UpdateException(" there are no user with such id BlogService");
        try {
            return userRepository.save(map(userShortDTO,User.class).setTeam(user.getTeam()));
        } catch (Exception e) {
            throw new UpdateException("UserService");
        }
    }

    @Override
    public User update(UserFullDTO userFullDTO) {
        return update(map(userFullDTO,User.class));
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
            try {
                userRepository.delete(userRepository.findOne(id));
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            throw new IdException("id must be not null");
        }
    }


    @Override
    public List<User> findByFirstName(String firstName) {
        if (firstName != null) {
            return userRepository.findByFirstName(firstName);
        } else {
            throw new FindException("firstName must be not null");
        }
    }

    @Override
    public List<User> findByLastName(String lastName) {
        if (lastName != null) {
            return userRepository.findByLastName(lastName);
        } else {
            throw new FindException("lastName must be not null");
        }
    }

    @Override
    public User findByUsername(String username) {
        if (username != null) {
            return userRepository.findByUsername(username);
        } else {
            throw new FindException("userName must be not null");
        }
    }

    @Override
    public User findByEmail(String email) {
        if (email != null) {
            return userRepository.findByEmail(email);
        } else {
            throw new FindException("email must be not null");
        }
    }


}
