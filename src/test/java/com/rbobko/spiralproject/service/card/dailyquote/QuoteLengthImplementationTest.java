package com.rbobko.spiralproject.service.card.dailyquote;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.rbobko.spiralproject.model.DailyQuoteCard;
import com.rbobko.spiralproject.model.StatusUpdateCard;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class QuoteLengthImplementationTest {

    private final QuoteLengthImplementation quoteLengthImplementation = new QuoteLengthImplementation();

    @Test
    void testCheck() {
        // Given
        var card = DailyQuoteCard.builder()
            .id(1L)
            .date(LocalDate.now())
            .message("quote1")
            .author("author1")
            .build();

        // When
        assertThat(quoteLengthImplementation.check(card, null)).isTrue();
    }

    @Test
    void testCheck_Empty() {
        // Given
        var card = DailyQuoteCard.builder()
            .id(1L)
            .date(LocalDate.now())
            .message("")
            .author("author1")
            .build();

        // When
        assertThat(quoteLengthImplementation.check(card, null)).isFalse();
    }

    @Test
    void testCheck_Short() {
        // Given
        var card = DailyQuoteCard.builder()
            .id(1L)
            .date(LocalDate.now())
            .message("qu")
            .author("author1")
            .build();

        // When
        assertThat(quoteLengthImplementation.check(card, null)).isFalse();
    }

    @Test
    void testCheck_Null() {
        // Given
        var card = DailyQuoteCard.builder()
            .id(1L)
            .date(LocalDate.now())
            .message(null)
            .author("author1")
            .build();

        // When
        assertThat(quoteLengthImplementation.check(card, null)).isFalse();
    }

    @Test
    void testCheck_NullCard() {
        // When
        assertThrows(IllegalArgumentException.class, () -> quoteLengthImplementation.check(null, null));
    }

    @Test
    void testCheck_StatusUpdateCard() {
        // Given
        var card = StatusUpdateCard.builder().build();

        // When
        assertThrows(IllegalArgumentException.class, () -> quoteLengthImplementation.check(card, null));
    }

}
