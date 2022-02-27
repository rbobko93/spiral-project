package com.rbobko.spiralproject.model;


import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DailyQuoteCard extends Card {

    @Transient
    @Default
    private String title = "Daily Quote";

    @NotEmpty
    private String author;

    @NotNull
    private LocalDate date;

    @Default
    @Transient
    private CardType type = CardType.DAILY_QUOTE;
}