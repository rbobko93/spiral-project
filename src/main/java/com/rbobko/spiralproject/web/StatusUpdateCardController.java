package com.rbobko.spiralproject.web;

import com.rbobko.spiralproject.model.StatusUpdateCard;
import com.rbobko.spiralproject.service.card.statusupdate.StatusUpdateCardService;
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
@RequestMapping("/api/status-update-cards")
@Slf4j
public class StatusUpdateCardController {

    private final StatusUpdateCardService statusUpdateCardService;

    public StatusUpdateCardController(StatusUpdateCardService statusUpdateCardService) {
        this.statusUpdateCardService = statusUpdateCardService;
    }

    // todo add create/update dto and logs

    @GetMapping("/{id}")
    public ResponseEntity<StatusUpdateCard> getById(@PathVariable final Long id) {
        return ResponseEntity.of(statusUpdateCardService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<StatusUpdateCard>> getAll() {
        return ResponseEntity.ok(statusUpdateCardService.findAll());
    }

    @PostMapping
    public ResponseEntity<StatusUpdateCard> createCard(@RequestBody @Valid StatusUpdateCard statusUpdateCard) {
        if (Objects.nonNull(statusUpdateCard.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(statusUpdateCardService.save(statusUpdateCard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusUpdateCard> update(@RequestBody @Valid StatusUpdateCard statusUpdateCard) {
        if (Objects.isNull(statusUpdateCard.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(statusUpdateCardService.save(statusUpdateCard));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long id) {
        statusUpdateCardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
