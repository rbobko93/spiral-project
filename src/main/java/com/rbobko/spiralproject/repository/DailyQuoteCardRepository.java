package com.rbobko.spiralproject.repository;

import com.rbobko.spiralproject.model.DailyQuoteCard;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyQuoteCardRepository extends JpaRepository<DailyQuoteCard, Long> {

    Optional<DailyQuoteCard> findByDate(LocalDate date);

}
