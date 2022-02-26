package com.rbobko.spiralproject.service;

import com.rbobko.spiralproject.model.Quote;
import com.rbobko.spiralproject.repository.QuoteRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Optional<Quote> findById(final Long id) {
        return quoteRepository.findById(id);
    }

    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    public Optional<Quote> findByDate(final LocalDate date) {
        log.debug("Fetching Quote by date: {}", date);
        return quoteRepository.findByDate(date);
    }

    public Quote save(final Quote quote) {
        return quoteRepository.save(quote);
    }

    public void deleteById(final Long id) {
        quoteRepository.deleteById(id);
    }
}
