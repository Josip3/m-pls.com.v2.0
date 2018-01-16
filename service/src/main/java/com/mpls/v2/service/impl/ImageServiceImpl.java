package com.mpls.v2.service.impl;

import com.mpls.v2.model.Image;
import com.mpls.v2.repository.ImageRepository;
import com.mpls.v2.service.GoogleDriveService;
import com.mpls.v2.service.ImageService;
import com.mpls.v2.service.exceptions.FindException;
import com.mpls.v2.service.exceptions.IdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    GoogleDriveService googleDriveService;

    @Override
    public Image save(Image image) {
      return imageRepository.save(image);
    }

    @Override
    public Image upload(MultipartFile multipartFile, Long id) {
        return imageRepository.save(imageRepository.findOne(id).setPath(googleDriveService.upload(multipartFile)));

    }

    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image findOne(Long id) {
        if (id != null || id >= 0) {
            return imageRepository.findOne(id);
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            imageRepository.delete(imageRepository.findOne(id));
            return true;
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Image findByOriginalName(String originalName) {
        if (originalName != null) {
            return imageRepository.findByOriginalName(originalName);
        } else {
            throw new FindException("OriginalName must be not null");
        }
    }
}
