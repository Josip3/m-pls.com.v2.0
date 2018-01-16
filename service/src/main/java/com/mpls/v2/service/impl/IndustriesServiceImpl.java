package com.mpls.v2.service.impl;

import com.mpls.v2.model.Industries;
import com.mpls.v2.repository.IndustriesRepository;
import com.mpls.v2.service.IndustriesService;
import com.mpls.v2.service.exceptions.FindException;
import com.mpls.v2.service.exceptions.IdException;
import com.mpls.v2.service.exceptions.SaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndustriesServiceImpl implements IndustriesService {

    @Autowired
    IndustriesRepository industriesRepository;

    @Override
    public Industries save(Industries industries) {
        if (industries != null) {
            return industriesRepository.save(industries);
        } else {
            throw new SaveException("Industries must be not null");
        }
    }

    @Override
    public List<Industries> findAll() {
        return industriesRepository.findAll();
    }

    @Override
    public Industries findOne(Long id) {
        if (id != null || id >= 0) {
            return industriesRepository.findOne(id);
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            try {
                industriesRepository.delete(industriesRepository.findOne(id));
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Industries findByName(String name) {
        if (name != null) {
            return industriesRepository.findByName(name);
        } else {
            throw new FindException("Name must be not null");
        }
    }
}
