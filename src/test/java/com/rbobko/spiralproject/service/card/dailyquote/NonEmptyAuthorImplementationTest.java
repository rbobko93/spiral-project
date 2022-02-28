package com.rbobko.spiralproject.service.card.dailyquote;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.rbobko.spiralproject.model.DailyQuoteCard;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class NonEmptyAuthorImplementationTest {

    private final NonEmptyAuthorImplementation nonEmptyAuthorImplementation = new NonEmptyAuthorImplementation();

    @Test
    void testCheck() {
        // Given
        var card = DailyQuoteCard.builder()
            .id(1L)
            .message("quote1")
            .author("author1")
            .date(LocalDate.now())
            .build();

        // When
        assertThat(nonEmptyAuthorImplementation.check(card, null)).isTrue();
    }

    @Test
    void testCheck_Empty() {
        // Given
        var card = DailyQuoteCard.builder()
            .id(1L)
            .message("quote1")
            .author("")
            .date(LocalDate.now())
            .build();

        // When
        assertThat(nonEmptyAuthorImplementation.check(card, null)).isFalse();
    }

    @Test
    void testCheck_Null() {
        // Given
        var card = DailyQuoteCard.builder()
            .id(1L)
            .message("quote1")
            .date(LocalDate.now())
            .build();

        // When
        assertThat(nonEmptyAuthorImplementation.check(card, null)).isFalse();
    }

    @Test
    void testCheck_NullCard() {
        // When
        assertThrows(IllegalArgumentException.class, () -> nonEmptyAuthorImplementation.check(null, null));
    }

}
