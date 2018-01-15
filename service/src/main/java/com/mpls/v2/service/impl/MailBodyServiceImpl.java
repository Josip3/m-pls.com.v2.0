package com.mpls.v2.service.impl;

import com.mpls.v2.model.MailBody;
import com.mpls.v2.repository.MailBodyRepository;
import com.mpls.v2.service.MailBodyService;
import com.mpls.v2.service.exceptions.FindException;
import com.mpls.v2.service.exceptions.IdException;
import com.mpls.v2.service.exceptions.SaveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailBodyServiceImpl implements MailBodyService {

    @Autowired
    MailBodyRepository mailBodyRepository;

    @Override
    public MailBody save(MailBody mailBody) {
        if (mailBody != null) {
            return mailBodyRepository.save(mailBody);
        } else {
            throw new SaveException("MailBody must be not null");
        }
    }

    @Override
    public List<MailBody> findAll() {
        return mailBodyRepository.findAll();
    }

    @Override
    public MailBody findOne(Long id) {
        if (id != null || id >= 0) {
            return mailBodyRepository.findOne(id);
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            mailBodyRepository.delete(mailBodyRepository.findOne(id));
            return true;
        } else {
            throw new IdException("id must be not null");
        }
    }

    @Override
    public MailBody findByName(String name) {
        if (name != null) {
            return mailBodyRepository.findByName(name);
        } else {
            throw new FindException("Name must be not null");
        }
    }
}
