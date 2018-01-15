package com.mpls.v2.service.impl;

import com.mpls.v2.model.Blog;
import com.mpls.v2.repository.BlogRepository;
import com.mpls.v2.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BlogServiceImpl implements BlogService{

    @Autowired
    BlogRepository blogRepository;

    @Override
    public Blog save(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findOne(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public Boolean delete(Long id) {
        blogRepository.delete(blogRepository.findOne(id));
        return true;
    }
}
