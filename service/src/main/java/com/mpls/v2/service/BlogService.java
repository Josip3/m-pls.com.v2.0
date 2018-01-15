package com.mpls.v2.service;

import com.mpls.v2.model.Blog;

import java.util.List;

public interface BlogService {

    Blog save(Blog blog);

    List<Blog> findAll();

    Blog findOne(Long id);

    Boolean delete(Long id);
}
