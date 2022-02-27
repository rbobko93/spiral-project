package com.rbobko.spiralproject.model.dto;


import com.rbobko.spiralproject.model.Card;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DailyQuoteCard extends Card {

    @NotEmpty
    private String author;
}
