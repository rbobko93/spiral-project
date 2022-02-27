package com.rbobko.spiralproject.web;

import com.rbobko.spiralproject.model.DailyQuoteCard;
import com.rbobko.spiralproject.service.card.dailyquote.DailyQuoteCardService;
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
@RequestMapping("/api/daily-quote-cards")
@Slf4j
public class DailyQuoteCardController {

    private final DailyQuoteCardService dailyQuoteCardService;

    public DailyQuoteCardController(DailyQuoteCardService dailyQuoteCardService) {
        this.dailyQuoteCardService = dailyQuoteCardService;
    }

    // todo add create/update dto and logs

    @GetMapping("/{id}")
    public ResponseEntity<DailyQuoteCard> getById(@PathVariable final Long id) {
        return ResponseEntity.of(dailyQuoteCardService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DailyQuoteCard>> getAll() {
        return ResponseEntity.ok(dailyQuoteCardService.findAll());
    }

    @PostMapping
    public ResponseEntity<DailyQuoteCard> createCard(@RequestBody @Valid DailyQuoteCard dailyQuoteCard) {
        if (Objects.nonNull(dailyQuoteCard.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(dailyQuoteCardService.save(dailyQuoteCard));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyQuoteCard> update(@RequestBody @Valid DailyQuoteCard dailyQuoteCard) {
        if (Objects.isNull(dailyQuoteCard.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(dailyQuoteCardService.save(dailyQuoteCard));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Long id) {
        dailyQuoteCardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
