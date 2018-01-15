package com.mpls.v2.repository;

import com.mpls.v2.model.MailBody;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailBodyRepository extends JpaRepository<MailBody, Integer> {

    MailBody findByName(String name);

}
