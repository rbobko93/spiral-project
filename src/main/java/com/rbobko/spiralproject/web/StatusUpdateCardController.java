package com.rbobko.spiralproject.web;

import com.rbobko.spiralproject.mapper.StatusUpdateCardMapper;
import com.rbobko.spiralproject.model.CardType;
import com.rbobko.spiralproject.model.StatusUpdateCard;
import com.rbobko.spiralproject.model.dto.StatusUpdateCardUpdateDTO;
import com.rbobko.spiralproject.service.card.statusupdate.StatusUpdateCardService;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final StatusUpdateCardMapper statusUpdateCardMapper;

    public StatusUpdateCardController(StatusUpdateCardService statusUpdateCardService,
        StatusUpdateCardMapper statusUpdateCardMapper) {
        this.statusUpdateCardService = statusUpdateCardService;
        this.statusUpdateCardMapper = statusUpdateCardMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusUpdateCard> getById(@PathVariable final Long id) {
        log.debug("REST request to get StatusUpdateCard with id: {}", id);
        return ResponseEntity.of(statusUpdateCardService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<StatusUpdateCard>> getAll() {
        log.debug("REST request to get all StatusUpdateCard");
        return ResponseEntity.ok(statusUpdateCardService.findAll());
    }

    @PostMapping
    public ResponseEntity<StatusUpdateCard> createCard(@RequestBody @Valid StatusUpdateCardUpdateDTO dto) {
        log.debug("REST request to create new StatusUpdateCard");
        if (Objects.nonNull(dto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        var statusUpdateCard = statusUpdateCardMapper.toEntity(dto);

        return ResponseEntity.ok(statusUpdateCardService.save(statusUpdateCard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusUpdateCard> update(@RequestBody @Valid StatusUpdateCardUpdateDTO dto) {
        var statusUpdateCard = statusUpdateCardMapper.toEntity(dto);
        if (Objects.isNull(statusUpdateCard.getId()) || !statusUpdateCard.getType().equals(CardType.STATUS_UPDATE)) {
            return ResponseEntity.badRequest().build();
        }

        log.debug("REST request to update StatusUpdateCard with id: {}", statusUpdateCard.getId());

        return ResponseEntity.ok(statusUpdateCardService.save(statusUpdateCard));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long id) {
        log.debug("REST request to delete StatusUpdateCard with id: {}", id);
        statusUpdateCardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
