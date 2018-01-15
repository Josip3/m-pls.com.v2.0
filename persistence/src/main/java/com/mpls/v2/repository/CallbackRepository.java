package com.mpls.v2.repository;

import com.mpls.v2.model.Callback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallbackRepository extends JpaRepository<Callback, Long> {

    List<Callback> findAllByName(String name);

    List<Callback> findAllByEmail(String email);

}
