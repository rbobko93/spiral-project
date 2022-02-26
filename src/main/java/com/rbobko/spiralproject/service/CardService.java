package com.rbobko.spiralproject.service;

import com.rbobko.spiralproject.model.Card;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class CardService {

    private final List<CardFeedProvider> cardFeedProviders;

    public CardService(List<CardFeedProvider> cardFeedProviders) {
        this.cardFeedProviders = cardFeedProviders;
    }

    @Transactional(readOnly = true)
    public List<Card> getCardsFeed() {
        log.debug("Fetching feed of Cards");
        var cards = retrieveCards();
        // todo add card implementations
        return cards;
    }

    @Transactional(readOnly = true)
    public List<Card> retrieveCards() {
        return cardFeedProviders.stream()
            .flatMap(cfd -> cfd.getCardFeed().stream())
            .collect(Collectors.toList());
    }

}
