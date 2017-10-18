package com.mpls.v2.controller;

import com.mpls.v2.model.User;
import com.mpls.v2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static java.util.Optional.ofNullable;

/**
 * Created by danul on 18.10.2017.
 */
@RestController
public class Test {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String test(Principal principal) {
        return ofNullable(principal).isPresent()+"";
    }

    @RequestMapping("/add")
    public User te() {
        return userService.save(new User().setName("admin").setPassword("admin"));
    }

}
