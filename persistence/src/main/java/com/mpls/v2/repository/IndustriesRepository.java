package com.mpls.v2.repository;

import com.mpls.v2.model.Industries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndustriesRepository extends JpaRepository<Industries,Long> {

    Industries findByName(String name);
}
