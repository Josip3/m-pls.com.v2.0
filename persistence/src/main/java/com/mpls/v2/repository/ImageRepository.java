package com.mpls.v2.repository;

import com.mpls.v2.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByOriginalName(String originalName);

}
