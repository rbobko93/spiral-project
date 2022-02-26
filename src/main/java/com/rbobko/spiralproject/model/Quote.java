package com.rbobko.spiralproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Quote {

    @Id
    @GeneratedValue
    private Long id;

    private String message;

    private String author;

}
