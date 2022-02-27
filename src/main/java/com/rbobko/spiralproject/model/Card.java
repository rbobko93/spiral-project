package com.rbobko.spiralproject.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@MappedSuperclass
public abstract class Card {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String message;

    @NotNull
    @Default
    private CardType type = CardType.UNKNOWN;
}
