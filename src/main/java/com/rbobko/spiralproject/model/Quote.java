package com.rbobko.spiralproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Quote {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String message;

    @NotEmpty
    private String author;

}
