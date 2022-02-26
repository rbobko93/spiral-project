package com.rbobko.spiralproject.service.card;

import com.rbobko.spiralproject.model.Card;

public interface CardImplementation<T extends Card> {

    boolean check(T card);

}
