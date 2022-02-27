package com.rbobko.spiralproject.model.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DailyQuoteCardUpdateDTO extends CardDTO {

    @NotEmpty
    private String author;

    @NotNull
    private LocalDate date;

}
