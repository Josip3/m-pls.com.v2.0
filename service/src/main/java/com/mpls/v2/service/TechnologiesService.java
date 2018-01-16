package com.mpls.v2.service;

import com.mpls.v2.model.Technologies;

import java.util.List;

public interface TechnologiesService {

    Technologies save(Technologies technologies);

    List<Technologies> findAll();

    Technologies findOne(Long id);

    Boolean delete(Long id);

    Technologies findByName(String name);
}
