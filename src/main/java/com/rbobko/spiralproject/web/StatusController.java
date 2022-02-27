package com.rbobko.spiralproject.web;

import com.rbobko.spiralproject.model.Status;
import com.rbobko.spiralproject.service.StatusService;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statuses")
@Slf4j
public class StatusController {

    private final StatusService service;

    public StatusController(StatusService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getById(@PathVariable final Long id) {
        log.debug("REST request to get Status with id: {}", id);
        return ResponseEntity.of(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Status>> getAll() {
        log.debug("REST request to get all Statuses");
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Status> createStatus(@RequestBody @Valid final Status status) {
        log.debug("REST request to save new Status");

        if (Objects.nonNull(status.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.save(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> updateStatus(@RequestBody @Valid final Status status) {
        if(Objects.isNull(status.getId())) {
            return ResponseEntity.badRequest().build();
        }

        log.debug("REST request to update Status with id: {}", status.getId());
        return ResponseEntity.ok(service.save(status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long id) {
        log.debug("REST request to delete Status with id: {}", id);
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
