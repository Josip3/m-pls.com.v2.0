package com.mpls.v2.controller;

import com.mpls.v2.dto.BlogFullDto;
import com.mpls.v2.dto.BlogShortDto;
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

    @GetMapping("/find-all")
    private ResponseEntity<List<BlogShortDto>> findAll() {
        return new ResponseEntity<List<BlogShortDto>>(blogService.findAll().stream().map(blog -> map(blog, BlogShortDto.class)).collect(toList()), HttpStatus.CREATED);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<BlogShortDto> findOne(@PathVariable Long id){
        Blog blog = blogService.findOne(id);
        if(blog == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<BlogShortDto>(map(blog, BlogFullDto.class), HttpStatus.OK);
    }

}
