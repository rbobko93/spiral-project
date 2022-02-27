package com.rbobko.spiralproject.service.card.dailyquote;

import com.rbobko.spiralproject.model.Card;
import com.rbobko.spiralproject.model.CardType;
import com.rbobko.spiralproject.model.DailyQuoteCard;
import com.rbobko.spiralproject.service.card.CardImplementation;

public abstract class DailyQuoteCardImplementation implements CardImplementation<DailyQuoteCard> {

    public CardType getType() {
        return CardType.DAILY_QUOTE;
    }

    public DailyQuoteCard toSpecificCardClass(Card card) {
        return (DailyQuoteCard) card;
    }

}
