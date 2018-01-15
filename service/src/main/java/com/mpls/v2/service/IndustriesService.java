package com.mpls.v2.service;

import com.mpls.v2.model.Industries;

import java.util.List;

public interface IndustriesService {

    Industries save(Industries industries);

    List<Industries> findAll();

    Industries findOne(Long id);

    Boolean delete(Long id);

    Industries findByName(String name);
}
