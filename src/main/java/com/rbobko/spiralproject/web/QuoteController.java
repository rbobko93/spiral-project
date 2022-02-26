package com.rbobko.spiralproject.web;

import com.rbobko.spiralproject.model.Quote;
import com.rbobko.spiralproject.service.QuoteService;
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
@RequestMapping("/api/quotes")
@Slf4j
public class QuoteController {

    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getById(@PathVariable final Long id) {
        log.debug("REST request to get Quote with id: {}", id);
        return ResponseEntity.of(quoteService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Quote>> getAll() {
        log.debug("REST request to get all Quotes");
        return ResponseEntity.ok(quoteService.findAll());
    }

    @PostMapping
    public ResponseEntity<Quote> createQuote(@RequestBody @Valid final Quote quote) {
        log.debug("REST request to save new Quote");

        if (Objects.nonNull(quote.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(quoteService.save(quote));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quote> updateQuote(@RequestBody @Valid final Quote quote) {
        if (Objects.isNull(quote.getId())) {
            return ResponseEntity.badRequest().build();
        }

        log.debug("REST request to update Quote with id: {}", quote.getId());
        return ResponseEntity.ok(quoteService.save(quote));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable final Long id) {
        log.debug("REST request to delete Quote with id: {}", id);
        quoteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
