package com.rbobko.spiralproject.repository;

import com.rbobko.spiralproject.model.Quote;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    Optional<Quote> findByDate(final LocalDate date);
}
