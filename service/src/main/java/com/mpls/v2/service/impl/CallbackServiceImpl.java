package com.mpls.v2.service.impl;

import com.mpls.v2.model.Callback;
import com.mpls.v2.repository.CallbackRepository;
import com.mpls.v2.service.CallbackService;
import com.mpls.v2.service.exceptions.IdException;
import com.mpls.v2.service.exceptions.SaveException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CallbackServiceImpl implements CallbackService{

    @Autowired
    CallbackRepository callbackRepository;

    @Override
    public Callback save(Callback callback) {

        if (callback != null) {
            return callbackRepository.save(callback);
        }else{
            throw new SaveException("Callback must be not null");
        }
}

    @Override
    public List<Callback> findAll() {
        return callbackRepository.findAll();
    }

    @Override
    public Callback findOne(Long id) {
        if (id != null || id >= 0) {
            return callbackRepository.findOne(id);
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            callbackRepository.delete(callbackRepository.findOne(id));
            return true;
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Callback findByName(String name) {
        if (name != null) {
            return callbackRepository.findByName(name);
        } else {
            throw new SaveException("name must be not null");
        }
    }

    @Override
    public Callback findByEmail(String email) {
        if (email != null) {
            return callbackRepository.findByEmail(email);
        } else {
            throw new SaveException("email must be not null");
        }
    }
}
