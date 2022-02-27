package com.rbobko.spiralproject.model.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class StatusUpdateCardUpdateDTO extends CardDTO {

    @NotEmpty
    private String title;

    @NotEmpty
    private String icon;

    @NotEmpty
    private String button;

}
