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
        return new ResponseEntity<List<BlogShortDto>>(blogService.findAll().stream().map(blog -> map(blog, BlogShortDto.class)).collect(toList()), HttpStatus.OK);
    }

    @GetMapping("/find-one/{id}")
    private ResponseEntity<BlogShortDto> findOne(@PathVariable Long id){
        Blog blog = blogService.findOne(id);
        if(blog == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<BlogShortDto>(map(blog, BlogFullDto.class), HttpStatus.OK);
    }

    @PutMapping("/update")
    private ResponseEntity<BlogFullDto> update(@RequestBody BlogFullDto blog){
        return new ResponseEntity<BlogFullDto>(map(blogService.update(blog), BlogFullDto.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable Long id){
        Boolean isDeleted = blogService.delete(id);
        return new ResponseEntity<Boolean>(isDeleted, isDeleted ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/find-by-header/{header}")
    private ResponseEntity<BlogShortDto> findByHeader(@PathVariable String header){
        Blog blog = blogService.findByHeader(header);
        if(blog == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<BlogShortDto>(map(blog, BlogShortDto.class), HttpStatus.OK);
    }

    @GetMapping("/find-all-by-industries-id/{id}")
    private ResponseEntity<List<BlogShortDto>> findAllByIndustries_Id(@PathVariable Long id){
        List<Blog> blogs = blogService.findAllByIndustries_Id(id);
        if(blogs == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<BlogShortDto>>(blogs.stream().map(blog -> map(blog, BlogShortDto.class)).collect(toList()), HttpStatus.OK);
    }
}
