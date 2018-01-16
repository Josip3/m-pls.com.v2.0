package com.mpls.v2.service.impl;

import com.mpls.v2.dto.TechnologiesDto;
import com.mpls.v2.model.Technologies;
import com.mpls.v2.repository.TechnologiesRepository;
import com.mpls.v2.service.TechnologiesService;
import com.mpls.v2.service.exceptions.FindException;
import com.mpls.v2.service.exceptions.IdException;
import com.mpls.v2.service.exceptions.SaveException;
import com.mpls.v2.service.exceptions.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mpls.v2.dto.utils.builder.Builder.map;

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
    public Technologies update(Technologies technologies) {
        if (technologies.getId() == null || technologies.getId() < 1)
            throw new UpdateException(" invalid id TechnologiesService");
        else if (technologiesRepository.findOne(technologies.getId()) == null)
            throw new UpdateException(" there are no technologies with such id ");
        try {
            return technologiesRepository.save(technologies);
        } catch (Exception e) {
            throw new UpdateException("TechnologiesService");
        }
    }

    @Override
    public Technologies update(TechnologiesDto technologiesDto) {
        return update(map(technologiesDto,Technologies.class));
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
