package com.mpls.v2.service;

import com.mpls.v2.dto.IndustriesFullDto;
import com.mpls.v2.dto.IndustriesShortDto;
import com.mpls.v2.model.Industries;

import java.util.List;

public interface IndustriesService {

    Industries save(Industries industries);

    Industries update(Industries industries);

    Industries update(IndustriesShortDto industriesShortDto);

    Industries update(IndustriesFullDto industriesFullDto);

    List<Industries> findAll();

    Industries findOne(Long id);

    Boolean delete(Long id);

    Industries findByName(String name);
}
