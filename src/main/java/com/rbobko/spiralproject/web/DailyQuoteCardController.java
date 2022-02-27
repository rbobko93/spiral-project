package com.rbobko.spiralproject.web;

import com.rbobko.spiralproject.mapper.DailyQuoteCardMapper;
import com.rbobko.spiralproject.model.CardType;
import com.rbobko.spiralproject.model.DailyQuoteCard;
import com.rbobko.spiralproject.model.dto.DailyQuoteCardUpdateDTO;
import com.rbobko.spiralproject.service.card.dailyquote.DailyQuoteCardService;
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
@RequestMapping("/api/daily-quote-cards")
@Slf4j
public class DailyQuoteCardController {

    private final DailyQuoteCardService dailyQuoteCardService;
    private final DailyQuoteCardMapper dailyQuoteCardMapper;

    public DailyQuoteCardController(DailyQuoteCardService dailyQuoteCardService,
        DailyQuoteCardMapper dailyQuoteCardMapper) {
        this.dailyQuoteCardService = dailyQuoteCardService;
        this.dailyQuoteCardMapper = dailyQuoteCardMapper;
    }

    // todo add create/update dto

    @GetMapping("/{id}")
    public ResponseEntity<DailyQuoteCard> getById(@PathVariable final Long id) {
        log.debug("REST request to get DailyQuoteCard with id: {}", id);
        return ResponseEntity.of(dailyQuoteCardService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DailyQuoteCard>> getAll() {
        log.debug("REST request to get all DailyQuoteCards");
        return ResponseEntity.ok(dailyQuoteCardService.findAll());
    }

    @PostMapping
    public ResponseEntity<DailyQuoteCard> createCard(@RequestBody @Valid DailyQuoteCardUpdateDTO dailyQuoteCard) {
        log.debug("REST request to create new DailyQuoteCard");
        if (Objects.nonNull(dailyQuoteCard.getId())) {
            return ResponseEntity.badRequest().build();
        }

        var entity = dailyQuoteCardMapper.toEntity(dailyQuoteCard);

        return ResponseEntity.ok(dailyQuoteCardService.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyQuoteCard> update(@RequestBody @Valid DailyQuoteCardUpdateDTO dto) {
        var dailyQuoteCard = dailyQuoteCardMapper.toEntity(dto);
        if (Objects.isNull(dailyQuoteCard.getId()) || !dailyQuoteCard.getType().equals(CardType.DAILY_QUOTE)) {
            return ResponseEntity.badRequest().build();
        }

        log.debug("REST request to update DailyQuoteCard with id: {}", dailyQuoteCard.getId());

        return ResponseEntity.ok(dailyQuoteCardService.save(dailyQuoteCard));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long id) {
        log.debug("REST request to delete DailyQuoteCard with id: {}", id);
        dailyQuoteCardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
