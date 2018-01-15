package com.mpls.v2.controller;

import com.mpls.v2.dto.UserFullDTO;
import com.mpls.v2.model.User;
import com.mpls.v2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.mpls.v2.dto.utils.builder.Builder.map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/get-user")
    private ResponseEntity<UserFullDTO> getUser(Principal principal){
        return new ResponseEntity<>(map(userService.findByUsername(principal.getName()),UserFullDTO.class),HttpStatus.OK);
    }

    @PostMapping("/save-user")
    private ResponseEntity<UserFullDTO> save(@RequestBody UserFullDTO user){
        return new ResponseEntity<>(map(userService.save(map(user,User.class)),UserFullDTO.class),HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<UserFullDTO> findOne(@PathVariable Long id){
        return new ResponseEntity<>(map(userService.findOne(id),UserFullDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<UserFullDTO> delete(@PathVariable Long id){
        return new ResponseEntity<>(map(userService.delete(id),UserFullDTO.class),HttpStatus.OK);
    }

    @GetMapping("/get-all")
    private ResponseEntity<UserFullDTO>findAll(){
        return new ResponseEntity<>(map(userService.findAll(),UserFullDTO.class),HttpStatus.OK);
    }

    @GetMapping("/find-by-email")
    private ResponseEntity<UserFullDTO> findByEmail(@RequestParam String name){
        return new ResponseEntity<>(map(userService.findByEmail(name),UserFullDTO.class),HttpStatus.OK);
    }

}

