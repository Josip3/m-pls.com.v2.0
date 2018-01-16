package com.mpls.v2.service;

import com.mpls.v2.dto.CallbackDTO;
import com.mpls.v2.model.Callback;

import java.util.List;

public interface CallbackService {

    Callback save(Callback callback);

    Callback update(Callback callback);

    Callback update(CallbackDTO callbackDTO);

    List<Callback> findAll();

    Callback findOne(Long id);

    Boolean delete(Long id);

    List<Callback> findByName(String name);

    List<Callback> findByEmail(String email);

}
