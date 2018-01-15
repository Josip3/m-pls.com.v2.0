package com.mpls.v2.service;

import com.mpls.v2.model.Callback;

import java.util.List;

public interface CallbackService {

    Callback save(Callback callback);

    List<Callback> findAll();

    Callback findOne(Long id);

    Boolean delete(Long id);

    Callback findByName(String name);

    Callback findByEmail(String email);

}
