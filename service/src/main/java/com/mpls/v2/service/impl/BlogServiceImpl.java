package com.mpls.v2.service.impl;

import com.mpls.v2.model.Blog;
import com.mpls.v2.repository.BlogRepository;
import com.mpls.v2.service.BlogService;
import com.mpls.v2.service.exceptions.IdException;
import com.mpls.v2.service.exceptions.SaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Override
    public Blog save(Blog blog) {
        if (blog != null) {
            return blogRepository.save(blog);
        } else {
            throw new SaveException("blog muast be not null");
        }

    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findOne(Long id) {
        if (id != null || id >= 0) {
            return blogRepository.findOne(id);
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            blogRepository.delete(blogRepository.findOne(id));
            return true;
        } else {
            throw new IdException("id must be not null");
        }
    }
}
