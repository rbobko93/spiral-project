package com.rbobko.spiralproject.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.rbobko.spiralproject.model.CardType;
import com.rbobko.spiralproject.model.DailyQuoteCard;
import com.rbobko.spiralproject.service.card.dailyquote.DailyQuoteCardService;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class DailyQuoteRepositoryIT {

    @Autowired
    private DailyQuoteCardRepository repository;

    @Test
    @Transactional
    void testFindByDate() {
        // Given
        var quote = repository.save(DailyQuoteCard.builder()
            .date(LocalDate.now())
            .message("quote1")
            .author("author1")
            .build());

        // When
        var result = repository.findByDate(LocalDate.now());

        // Then
        assertThat(result).hasSize(1);
        var card = result.get(0);

        assertThat(card.getId()).isEqualTo(quote.getId());
        assertThat(card.getDate()).isEqualTo(quote.getDate());
        assertThat(card.getAuthor()).isEqualTo(quote.getAuthor());
        assertThat(card.getTitle()).isEqualTo(DailyQuoteCardService.CARD_TITLE);
        assertThat(card.getType()).isEqualTo(CardType.DAILY_QUOTE);
    }

    @Test
    @Transactional
    void testFindByDate_MultipleCards() {
        // Given
        var quote = repository.save(DailyQuoteCard.builder()
            .date(LocalDate.now())
            .message("quote1")
            .author("author1")
            .build());

        var quote2 = repository.save(DailyQuoteCard.builder()
            .date(LocalDate.now().minusDays(1))
            .message("quote2")
            .author("autho2")
            .build());

        // When
        var result = repository.findByDate(LocalDate.now());

        // Then
        assertThat(result).hasSize(1);
        var card = result.get(0);

        assertThat(card.getId()).isEqualTo(quote.getId());
        assertThat(card.getDate()).isEqualTo(quote.getDate());
        assertThat(card.getAuthor()).isEqualTo(quote.getAuthor());
        assertThat(card.getTitle()).isEqualTo(DailyQuoteCardService.CARD_TITLE);
        assertThat(card.getType()).isEqualTo(CardType.DAILY_QUOTE);
    }

    @Test
    @Transactional
    void testFindByDate_NoCards() {
        // Given
        var quote = repository.save(DailyQuoteCard.builder()
            .date(LocalDate.now().minusDays(1))
            .message("quote1")
            .author("author1")
            .build());

        // When
        var result = repository.findByDate(LocalDate.now());

        // Then
        assertThat(result).hasSize(0);
    }

}
