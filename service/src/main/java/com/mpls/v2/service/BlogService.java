package com.mpls.v2.service;

import com.mpls.v2.dto.BlogDto;
import com.mpls.v2.model.Blog;

import java.util.List;

public interface BlogService {

    Blog save(Blog blog);

    Blog update(BlogDto blogDto);

    List<Blog> findAll();

    Blog findOne(Long id);

    Boolean delete(Long id);

    Blog findByHeader(String header);

    List<Blog> findAllByIndustries_Id(Long id);
}
