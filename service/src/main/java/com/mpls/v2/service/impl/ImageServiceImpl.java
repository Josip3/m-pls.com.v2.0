package com.mpls.v2.service.impl;

import com.mpls.v2.model.Image;
import com.mpls.v2.repository.ImageRepository;
import com.mpls.v2.service.ImageService;
import com.mpls.v2.service.exceptions.FindException;
import com.mpls.v2.service.exceptions.IdException;
import com.mpls.v2.service.exceptions.SaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    ImageRepository imageRepository;

    @Override
    public Image save(Image image) {
        if (image != null) {
            return imageRepository.save(image);
        }else{
            throw new SaveException("Image must be not null");
        }
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
