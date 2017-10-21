package com.mpls.v2.helper.test;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by Kishka on 21.10.2017.
 */
@RestController
@RequestMapping("/t")
public class Testtt {

    @RequestMapping("/principal")
    private ResponseEntity<Principal> getPrincipal(Principal principal){
        return new ResponseEntity<Principal>(principal, HttpStatus.OK);
    }

}
