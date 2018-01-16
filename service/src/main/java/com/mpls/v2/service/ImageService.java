package com.mpls.v2.service;

import com.mpls.v2.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    Image save(Image image);

    Image update(Image image);

    Image upload(MultipartFile multipartFile,Long id);

    List<Image> findAll();

    Image findOne(Long id);

    Boolean delete(Long id);

    Image findByOriginalName(String originalName);
}
