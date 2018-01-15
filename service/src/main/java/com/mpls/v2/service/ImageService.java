package com.mpls.v2.service;

import com.mpls.v2.model.Image;

import java.util.List;

public interface ImageService {

    Image save(Image image);

    List<Image> findAll();

    Image findOne(Long id);

    Boolean delete(Long id);

    Image findByOriginalName(String originalName);
}
