package com.rbobko.spiralproject.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.rbobko.spiralproject.model.CardType;
import com.rbobko.spiralproject.model.dto.StatusUpdateCardUpdateDTO;
import org.junit.jupiter.api.Test;

class StatusUpdateCardMapperTest {

    private final StatusUpdateCardMapper mapper = new com.rbobko.spiralproject.mapper.StatusUpdateCardMapperImpl();

    @Test
    void testToEntity() {
        // Given
        var dto = StatusUpdateCardUpdateDTO.builder()
            .id(1L)
            .title("custom title")
            .message("custom message")
            .icon("icon string")
            .button("button text")
            .build();

        // When
        var result = mapper.toEntity(dto);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(dto.getId());
        assertThat(result.getTitle()).isEqualTo(dto.getTitle());
        assertThat(result.getMessage()).isEqualTo(dto.getMessage());
        assertThat(result.getIcon()).isEqualTo(dto.getIcon());
        assertThat(result.getButton()).isEqualTo(dto.getButton());
        assertThat(result.getType()).isEqualTo(CardType.STATUS_UPDATE);
    }


}
