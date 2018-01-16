package com.mpls.v2.repository;

import com.mpls.v2.model.Technologies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologiesRepository extends JpaRepository<Technologies,Long> {

    Technologies findByName(String name);
}
