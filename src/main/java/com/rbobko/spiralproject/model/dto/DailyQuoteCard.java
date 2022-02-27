package com.rbobko.spiralproject.model.dto;


import javax.validation.constraints.NotEmpty;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DailyQuoteCard extends Card {

    @NotEmpty
    private String author;

    @Default
    private CardType type = CardType.DAILY_QUOTE;
}
