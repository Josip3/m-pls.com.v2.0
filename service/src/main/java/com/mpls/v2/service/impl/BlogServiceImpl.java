package com.mpls.v2.service.impl;

import com.mpls.v2.dto.BlogShortDto;
import com.mpls.v2.dto.BlogFullDto;
import com.mpls.v2.model.Blog;
import com.mpls.v2.model.Industries;
import com.mpls.v2.repository.BlogRepository;
import com.mpls.v2.service.BlogService;
import com.mpls.v2.service.IndustriesService;
import com.mpls.v2.service.exceptions.FindException;
import com.mpls.v2.service.exceptions.IdException;
import com.mpls.v2.service.exceptions.SaveException;
import com.mpls.v2.service.exceptions.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.mpls.v2.dto.utils.builder.Builder.map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    IndustriesService industriesService;

    @Override
    public Blog save(Blog blog) {
        if (blog != null) {
            return blogRepository.save(blog);
        } else {
            throw new SaveException("blog muast be not null");
        }

    }

    @Override
    public Blog update(BlogShortDto blogDto) {
       Blog blog;
        if (blogDto.getId() == null || blogDto.getId() < 1)
            throw new UpdateException(" invalid id BlogService");
        else if ((blog = blogRepository.findOne(blogDto.getId())) == null)
            throw new UpdateException(" there are no blog with such id BlogService");
        try {
            return blogRepository.save(map(blogDto, Blog.class).setIndustries(blog.getIndustries()));
        } catch (Exception e) {
            throw new UpdateException("BlogService");
        }
    }


    @Override
    public Blog update(Blog blog) {
        if (blog.getId() == null || blog.getId() < 1)
            throw new UpdateException(" invalid id blogService");
        else if (blogRepository.findOne(blog.getId()) == null)
            throw new UpdateException(" there are no blog with such id BlogService");
        try {
            return blogRepository.save(blog);
        } catch (Exception e) {
            throw new UpdateException("BlogService");
        }
    }


    @Override
    public Blog update(BlogFullDto blogFullDto) {
        Industries industries = industriesService.findOne(blogFullDto.getIndustries().getId());
        if (industries != null) {
            return update(map(blogFullDto,Blog.class).setIndustries(industries));
        } else {
            throw new UpdateException("Industries must be not null");
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
            try {
                blogRepository.delete(blogRepository.findOne(id));
                return true;
            } catch (Exception e){
                return false;
            }
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Blog findByHeader(String header) {
        if (header != null) {
            return blogRepository.findByHeader(header);
        } else {
            throw new FindException("find for your header give any value");
        }
    }

    @Override
    public List<Blog> findAllByIndustries_Id(Long id) {

        if (id != null) {
            return blogRepository.findAllByIndustries_Id(id);
        }else {
            throw new IdException("id must be not null");
        }
    }
}
