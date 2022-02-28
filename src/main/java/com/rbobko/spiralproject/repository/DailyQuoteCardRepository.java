package com.rbobko.spiralproject.repository;

import com.rbobko.spiralproject.model.DailyQuoteCard;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyQuoteCardRepository extends JpaRepository<DailyQuoteCard, Long> {

    List<DailyQuoteCard> findByDate(LocalDate date);

}
