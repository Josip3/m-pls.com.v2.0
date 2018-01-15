package com.mpls.v2.repository;

import com.mpls.v2.model.Callback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CallbackRepository extends JpaRepository<Callback, Integer> {

    Callback findByName(String name);

    Callback findByEmail(String email);

}
