package com.rbobko.spiralproject.service.card;

import com.rbobko.spiralproject.model.Card;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface CardFeedProvider {

    List<Card> getCardFeed(final HttpServletRequest request);

}
