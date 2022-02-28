package com.rbobko.spiralproject.model.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CardDTO {

    private Long id;

    @NotEmpty
    private String message;

}
