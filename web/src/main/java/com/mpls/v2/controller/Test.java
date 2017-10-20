package com.mpls.v2.controller;

import com.google.api.services.drive.model.File;
import com.mpls.v2.model.User;
import com.mpls.v2.service.GoogleDriveService;
import com.mpls.v2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

/**
 * Created by danul on 18.10.2017.
 */
@RestController
public class Test {

    @Autowired
    GoogleDriveService googleDriveService;
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String test(Principal principal) {
        return ofNullable(principal).isPresent() + "";
    }

    @RequestMapping("/add-file")
    public void addFile(MultipartFile multipartFile) {
//        googleDriveService.createFolder("test", "");
        googleDriveService.upload(multipartFile, "test");
        ;
//        System.err.println(googleDriveService.getAll().size());
    }


    @RequestMapping("/add")
    public User te() {
        return userService.save(new User().setName("admin").setPassword("admin"));
    }

    @RequestMapping("/get")
    public String get() {
//        76d6f5df-2f74-4b44-b77f-8489a78a34b1.jpg
        return this.googleDriveService.getAll().toString() + "    |size:" + this.googleDriveService.getAll().size();
    }
    @RequestMapping("/getAllImaget")
    public List<String> getAllImg(){
        return this.googleDriveService.getAll().stream().map(File::getId).collect(toList());
    }

}
