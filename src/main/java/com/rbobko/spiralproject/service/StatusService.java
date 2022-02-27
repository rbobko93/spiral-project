package com.rbobko.spiralproject.service;

import com.rbobko.spiralproject.model.Status;
import com.rbobko.spiralproject.repository.StatusRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Optional<Status> findById(final Long id) {
        return statusRepository.findById(id);
    }

    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    public Status save(final Status status) {
        return statusRepository.save(status);
    }

    public void deleteById(final Long id) {
        statusRepository.deleteById(id);
    }
}
