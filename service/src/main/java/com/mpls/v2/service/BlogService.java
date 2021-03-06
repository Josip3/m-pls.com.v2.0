package com.mpls.v2.service;

import com.mpls.v2.dto.BlogShortDto;
import com.mpls.v2.dto.BlogFullDto;
import com.mpls.v2.model.Blog;

import java.util.List;

public interface BlogService {

    Blog save(Blog blog);

    Blog update(BlogShortDto blogShortDto);

    Blog update(Blog blog);

    Blog update(BlogFullDto blogFullDto);

    List<Blog> findAll();

    Blog findOne(Long id);

    Boolean delete(Long id);

    Blog findByHeader(String header);

    List<Blog> findAllByIndustries_Id(Long id);
}
