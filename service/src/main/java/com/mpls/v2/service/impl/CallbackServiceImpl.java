package com.mpls.v2.service.impl;

import com.mpls.v2.dto.CallbackDTO;
import com.mpls.v2.model.Callback;
import com.mpls.v2.repository.CallbackRepository;
import com.mpls.v2.service.CallbackService;
import com.mpls.v2.service.exceptions.FindException;
import com.mpls.v2.service.exceptions.IdException;
import com.mpls.v2.service.exceptions.SaveException;
import com.mpls.v2.service.exceptions.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mpls.v2.dto.utils.builder.Builder.map;

@Service
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
    public Callback update(Callback callback) {
        if (callback.getId() == null || callback.getId() < 1)
            throw new UpdateException(" invalid id CallbackService");
        else if (callbackRepository.findOne(callback.getId()) == null)
            throw new UpdateException(" there are no callback with such id ");
        try {
            return callbackRepository.save(callback);
        } catch (Exception e) {
            throw new UpdateException("CallbackService");
        }
    }

    @Override
    public Callback update(CallbackDTO callbackDTO) {
        return update(map(callbackDTO,Callback.class));
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
            try {
                callbackRepository.delete(callbackRepository.findOne(id));
                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public List<Callback> findByName(String name) {
        if (name != null) {
            return callbackRepository.findAllByName(name);
        } else {
            throw new FindException("name must be not null");
        }
    }

    @Override
    public List<Callback> findByEmail(String email) {
        if (email != null) {
            return callbackRepository.findAllByEmail(email);
        } else {
            throw new FindException("email must be not null");
        }
    }
}
