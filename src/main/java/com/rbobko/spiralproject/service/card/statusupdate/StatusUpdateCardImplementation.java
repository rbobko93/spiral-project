package com.rbobko.spiralproject.service.card.statusupdate;

import com.rbobko.spiralproject.model.Card;
import com.rbobko.spiralproject.model.CardType;
import com.rbobko.spiralproject.model.StatusUpdateCard;
import com.rbobko.spiralproject.service.card.CardImplementation;

public abstract class StatusUpdateCardImplementation implements CardImplementation<StatusUpdateCard> {

    public CardType getType() {
        return CardType.STATUS_UPDATE;
    }

    public StatusUpdateCard toSpecificCardClass(Card card) {
        return (StatusUpdateCard) card;
    }

}
