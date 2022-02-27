package com.rbobko.spiralproject.model.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public abstract class Card {

    @NotEmpty
    private String title;

    @NotEmpty
    private String message;
}
