package com.mpls.v2.service.impl;

import com.mpls.v2.model.Group;
import com.mpls.v2.repository.GroupRepository;
import com.mpls.v2.service.GroupService;
import com.mpls.v2.service.exceptions.FindException;
import com.mpls.v2.service.exceptions.IdException;
import com.mpls.v2.service.exceptions.SaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    GroupRepository groupRepository;


    @Override
    public Group save(Group group) {
        if (group != null) {
            return groupRepository.save(group);
        }else{
            throw new SaveException("Group must be not null");
        }
    }

    @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Group findOne(Long id) {
        if (id != null || id >= 0) {
            return groupRepository.findOne(id);
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            groupRepository.delete(groupRepository.findOne(id));
            return true;
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Group findByName(String name) {
        if (name != null) {
            return groupRepository.findByName(name);
        } else {
            throw new FindException("name must be not null");
        }
    }
}
