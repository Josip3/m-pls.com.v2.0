package com.mpls.v2.repository;

import com.mpls.v2.model.Callback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallbackRepository extends JpaRepository<Callback, Long> {

    Callback findByName(String name);

    Callback findByEmail(String email);

}
