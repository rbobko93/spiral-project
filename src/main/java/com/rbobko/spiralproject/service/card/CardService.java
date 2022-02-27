package com.rbobko.spiralproject.service.card;

import com.rbobko.spiralproject.model.dto.Card;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class CardService {

    private final List<CardFeedProvider<?>> cardFeedProviders;

    public CardService(List<CardFeedProvider<?>> cardFeedProviders) {
        this.cardFeedProviders = cardFeedProviders;
    }

    @Transactional(readOnly = true)
    public List<Card> getCardsFeed(final HttpServletRequest request) {
        log.debug("Fetching feed of Cards");
        var cards = retrieveCards(request);
        // todo add card implementations
        return cards;
    }

    @Transactional(readOnly = true)
    public List<Card> retrieveCards(final HttpServletRequest request) {
        return cardFeedProviders.stream()
            .flatMap(cfd -> cfd.getCardFeed(request).stream())
            .collect(Collectors.toList());
    }

}
