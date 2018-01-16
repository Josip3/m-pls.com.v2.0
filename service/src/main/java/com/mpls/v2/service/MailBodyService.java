package com.mpls.v2.service;

import com.mpls.v2.dto.MailBodyDTO;
import com.mpls.v2.model.MailBody;

import java.util.List;

public interface MailBodyService {

    MailBody save(MailBody mailBody);

    MailBody update(MailBody mailBody);

    MailBody update(MailBodyDTO mailBodyDTO);

    List<MailBody> findAll();

    MailBody findOne(Long id);

    Boolean delete(Long id);

    MailBody findByName(String name);
}
