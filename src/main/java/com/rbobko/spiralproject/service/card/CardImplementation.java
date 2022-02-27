package com.rbobko.spiralproject.service.card;

import com.rbobko.spiralproject.model.Card;
import com.rbobko.spiralproject.model.CardType;

public interface CardImplementation<E extends Card> {

    boolean check(Card card);
    CardType getType();
    E toSpecificCardClass(Card card);

}
