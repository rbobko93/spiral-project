package com.rbobko.spiralproject.service.card.dailyquote;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.rbobko.spiralproject.model.CardType;
import com.rbobko.spiralproject.model.DailyQuoteCard;
import com.rbobko.spiralproject.repository.DailyQuoteCardRepository;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class DailyQuoteCardServiceIT {

    @Autowired
    private DailyQuoteCardService dailyQuoteCardService;

    @Autowired
    private DailyQuoteCardRepository dailyQuoteCardRepository;

    private DailyQuoteCard c1;
    private DailyQuoteCard c2;

    @BeforeEach
    void beforeEach() {
        c1 = dailyQuoteCardRepository.save(DailyQuoteCard.builder()
            .date(LocalDate.now(ZoneOffset.UTC).minusDays(1))
            .message("quote1")
            .author("author1")
            .build());

        c2 = dailyQuoteCardRepository.save(DailyQuoteCard.builder()
            .date(LocalDate.now(ZoneOffset.UTC))
            .message("quote2")
            .author("author2")
            .build());
    }

    @Test
    void testFindById_NotFound() {
        // When
        var result = dailyQuoteCardService.findById(123L);

        // Then
        assertThat(result).isEmpty();
    }

    @Test
    void testFindById() {
        // When
        var result = dailyQuoteCardService.findById(c1.getId());

        // Then
        assertThat(result).isPresent();
        var entity = result.get();
        assertThat(entity.getId()).isEqualTo(c1.getId());
        assertThat(entity.getTitle()).isEqualTo(c1.getTitle());
        assertThat(entity.getAuthor()).isEqualTo(c1.getAuthor());
        assertThat(entity.getMessage()).isEqualTo(c1.getMessage());
        assertThat(entity.getDate()).isEqualTo(c1.getDate());
        assertThat(entity.getType()).isEqualTo(CardType.DAILY_QUOTE);
    }

    @Test
    void testFindAll() {
        // When
        var result = dailyQuoteCardService.findAll();

        // Then
        assertThat(result).hasSize(2).contains(c1, c2);
    }

    @Test
    void testSave() {
        // Given
        var card = DailyQuoteCard.builder()
            .date(LocalDate.now())
            .message("q1")
            .author("a1")
            .title("new title?")
            .build();

        // When
        var result = dailyQuoteCardService.save(card);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getMessage()).isEqualTo(card.getMessage());
        assertThat(result.getAuthor()).isEqualTo(card.getAuthor());
        assertThat(result.getType()).isEqualTo(CardType.DAILY_QUOTE);
        assertThat(result.getDate()).isEqualTo(card.getDate());
        assertThat(result.getTitle()).isEqualTo(DailyQuoteCardService.CARD_TITLE);
    }

    @Test
    void testDeleteById() {
        // Given
        assertThat(dailyQuoteCardRepository.count()).isEqualTo(2);

        // When
        dailyQuoteCardService.deleteById(c1.getId());

        // Then
        assertThat(dailyQuoteCardRepository.count()).isEqualTo(1);
        var result = dailyQuoteCardRepository.findAll().get(0);

        assertThat(result.getId()).isEqualTo(c2.getId());
    }

    @Test
    void testDeleteById_NotFound() {
        // Given
        assertThat(dailyQuoteCardRepository.count()).isEqualTo(2);

        // When
        assertThrows(EmptyResultDataAccessException.class, () -> dailyQuoteCardService.deleteById(123L));

        // Then
        assertThat(dailyQuoteCardRepository.count()).isEqualTo(2);
    }

    @Test
    void testGetCardFeed() {
        // When
        var result = dailyQuoteCardService.getCardFeed();

        // Then
        assertThat(result).hasSize(1);
        var card = result.get(0);

        assertThat(card.getId()).isEqualTo(c2.getId());
        assertThat(card.getMessage()).isEqualTo(c2.getMessage());
        assertThat(card.getAuthor()).isEqualTo(c2.getAuthor());
        assertThat(card.getDate()).isEqualTo(LocalDate.now(ZoneOffset.UTC));
        assertThat(card.getType()).isEqualTo(CardType.DAILY_QUOTE);
        assertThat(card.getTitle()).isEqualTo(DailyQuoteCardService.CARD_TITLE);
    }

    @Test
    void testGetCardFeed_Empty() {
        // Given
        dailyQuoteCardRepository.deleteAll();
        assertThat(dailyQuoteCardRepository.count()).isZero();

        // When
        var result = dailyQuoteCardService.getCardFeed();

        // Then
        assertThat(result).isEmpty();
    }

}
