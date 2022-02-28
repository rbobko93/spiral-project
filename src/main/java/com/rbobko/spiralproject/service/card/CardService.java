package com.rbobko.spiralproject.service.card;

import com.rbobko.spiralproject.model.Card;
import com.rbobko.spiralproject.model.CardType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    private final Map<CardType, List<CardImplementation<?>>> cardImplementationTypeMap;

    public CardService(List<CardFeedProvider<?>> cardFeedProviders, List<CardImplementation<?>> cardImplementations) {
        this.cardFeedProviders = cardFeedProviders;
        this.cardImplementationTypeMap = cardImplementations.stream()
            .collect(Collectors.groupingBy(CardImplementation::getType));
    }

    @Transactional(readOnly = true)
    public List<Card> getCardsFeed(final HttpServletRequest request) {
        log.debug("Fetching feed of Cards");
        return retrieveCards()
            .stream().filter(c -> checkImplementations(c, request))
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<Card> retrieveCards() {
        return cardFeedProviders.stream()
            .flatMap(cfd -> cfd.getCardFeed().stream())
            .collect(Collectors.toList());
    }

    public boolean checkImplementations(Card card, HttpServletRequest request) {
        var type = card.getType();
        var implementations = Optional.ofNullable(cardImplementationTypeMap.get(type))
            .orElse(Collections.emptyList());

        for (CardImplementation<?> implementation : implementations) {
            if (!implementation.check(card, request)) {
                return false;
            }
        }

        return true;
    }

}
