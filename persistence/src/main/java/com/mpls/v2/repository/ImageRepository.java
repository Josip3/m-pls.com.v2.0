package com.mpls.v2.repository;

import com.mpls.v2.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    Image findByOriginalName(String originalName);

}
