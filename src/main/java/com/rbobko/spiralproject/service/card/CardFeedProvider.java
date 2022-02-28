package com.rbobko.spiralproject.service.card;

import com.rbobko.spiralproject.model.Card;
import java.util.List;

public interface CardFeedProvider<T extends Card> {

    List<T> getCardFeed();

}
