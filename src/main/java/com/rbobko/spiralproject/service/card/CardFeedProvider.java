package com.rbobko.spiralproject.service.card;

import com.rbobko.spiralproject.model.dto.Card;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface CardFeedProvider<T extends Card> {

    List<Card> getCardFeed(final HttpServletRequest request);
    boolean checkImplementations(T card);

}
