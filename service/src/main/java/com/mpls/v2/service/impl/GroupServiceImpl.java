package com.mpls.v2.service.impl;

import com.mpls.v2.dto.GroupFullDTO;
import com.mpls.v2.dto.GroupShortDTO;
import com.mpls.v2.model.Group;
import com.mpls.v2.model.User;
import com.mpls.v2.repository.GroupRepository;
import com.mpls.v2.service.GroupService;
import com.mpls.v2.service.UserService;
import com.mpls.v2.service.exceptions.FindException;
import com.mpls.v2.service.exceptions.IdException;
import com.mpls.v2.service.exceptions.SaveException;
import com.mpls.v2.service.exceptions.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mpls.v2.dto.utils.builder.Builder.map;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserService userService;


    @Override
    public Group save(Group group) {
        if (group != null) {
            return groupRepository.save(group);
        }else{
            throw new SaveException("Group must be not null");
        }
    }


    @Override
    public Group update(Group group) {
        if (group.getId() == null || group.getId() < 1)
            throw new UpdateException(" invalid id GroupService");
        else if (groupRepository.findOne(group.getId()) == null)
            throw new UpdateException(" there are no group with such id GroupService");
        try {
            return groupRepository.save(group);
        } catch (Exception e) {
            throw new UpdateException("GroupService");
        }
    }

    @Override
    public Group update(GroupShortDTO groupShortDTO) {
        Group group;
        if (groupShortDTO.getId() == null || groupShortDTO.getId() < 1)
            throw new UpdateException(" invalid id GroupService");
        else if ((group= groupRepository.findOne(groupShortDTO.getId())) == null)
            throw new UpdateException(" there are no group with such id ");
        try {
            return groupRepository.save(map(groupShortDTO,Group.class).setUsers(group.getUsers()));
        } catch (Exception e) {
            throw new UpdateException("GroupService");
        }
    }

    @Override
    public Group update(GroupFullDTO groupFullDTO) {
        return update(map(groupFullDTO,Group.class));
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
            try {
                groupRepository.delete(groupRepository.findOne(id));
                return true;
            } catch (Exception e) {
                return false;
            }
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
