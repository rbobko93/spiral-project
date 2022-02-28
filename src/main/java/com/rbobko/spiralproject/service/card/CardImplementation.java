package com.rbobko.spiralproject.service.card;

import com.rbobko.spiralproject.model.Card;
import com.rbobko.spiralproject.model.CardType;
import javax.servlet.http.HttpServletRequest;

public interface CardImplementation<E extends Card> {

    boolean check(Card card, HttpServletRequest request);
    CardType getType();
    E toSpecificCardClass(Card card);

}
