package com.mpls.v2.repository;

import com.mpls.v2.model.MailBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailBodyRepository extends JpaRepository<MailBody, Long> {

    MailBody findByName(String name);

}
