package com.mpls.v2.repository;

import com.mpls.v2.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {

    Blog findByHeader(String header);

    List<Blog> findAllByIndustries_Id(Long id);




}
