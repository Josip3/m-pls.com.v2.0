package com.mpls.v2.service.impl;

import com.mpls.v2.model.Technologies;
import com.mpls.v2.repository.TechnologiesRepository;
import com.mpls.v2.service.TechnologiesService;
import com.mpls.v2.service.exceptions.FindException;
import com.mpls.v2.service.exceptions.IdException;
import com.mpls.v2.service.exceptions.SaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologiesServiceImpl implements TechnologiesService{

    @Autowired
    TechnologiesRepository technologiesRepository;

    @Override
    public Technologies save(Technologies technologies) {
        if (technologies != null) {
            return technologiesRepository.save(technologies);
        } else {
            throw new SaveException("Technologies must be not null");
        }
    }

    @Override
    public List<Technologies> findAll() {
        return technologiesRepository.findAll();
    }

    @Override
    public Technologies findOne(Long id) {
        if (id != null || id >= 0) {
            return technologiesRepository.findOne(id);
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            try {
                technologiesRepository.delete(technologiesRepository.findOne(id));
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Technologies findByName(String name) {
        if (name != null) {
            return technologiesRepository.findByName(name);
        } else {
            throw new FindException("Name must be not null");
        }
    }
}
