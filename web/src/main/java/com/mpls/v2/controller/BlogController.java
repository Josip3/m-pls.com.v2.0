package com.mpls.v2.controller;

import com.mpls.v2.dto.BlogDto;
import com.mpls.v2.dto.BlogFullDto;
import com.mpls.v2.model.Blog;
import com.mpls.v2.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mpls.v2.dto.utils.builder.Builder.map;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/save")
    private ResponseEntity<BlogFullDto> save(@RequestBody BlogFullDto blog) {
        return new ResponseEntity<BlogFullDto>(map(blogService.save(map(blog, Blog.class)), BlogFullDto.class), HttpStatus.CREATED);
    }

    @GetMapping("/findAll")
    private ResponseEntity<List<BlogDto>> findAll() {
        return new ResponseEntity<List<BlogDto>>(blogService.findAll().stream().map(blog -> map(blog, BlogDto.class)).collect(toList()), HttpStatus.CREATED);
    }

}
