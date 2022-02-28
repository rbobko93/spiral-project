package com.rbobko.spiralproject.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.rbobko.spiralproject.model.CardType;
import com.rbobko.spiralproject.model.dto.DailyQuoteCardUpdateDTO;
import com.rbobko.spiralproject.service.card.dailyquote.DailyQuoteCardService;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class DailyQuoteCardMapperTest {

    private final DailyQuoteCardMapper dailyQuoteCardMapper = new com.rbobko.spiralproject.mapper.DailyQuoteCardMapperImpl();

    @Test
    void testToEntity() {
        // Given
        var dto = DailyQuoteCardUpdateDTO.builder()
            .id(1L)
            .author("author1")
            .message("message")
            .date(LocalDate.now()).build();

        // When
        var result = dailyQuoteCardMapper.toEntity(dto);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(dto.getId());
        assertThat(result.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(result.getMessage()).isEqualTo(dto.getMessage());
        assertThat(result.getDate()).isEqualTo(dto.getDate());
        assertThat(result.getTitle()).isEqualTo(DailyQuoteCardService.CARD_TITLE);
        assertThat(result.getType()).isEqualTo(CardType.DAILY_QUOTE);
    }
}
