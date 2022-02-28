package com.rbobko.spiralproject.model;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class StatusUpdateCard extends Card {

    @NotEmpty
    private String title;

    @NotEmpty
    private String icon;

    @NotEmpty
    private String button;

    @Default
    @Transient
    private CardType type = CardType.STATUS_UPDATE;

}
