package com.rbobko.spiralproject.service.card;

import com.rbobko.spiralproject.model.Card;
import com.rbobko.spiralproject.model.CardType;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class CardImplementationDispatcher {

    private Map<CardType, List<CardImplementation<?>>> cardImplementationTypeMap;

    public CardImplementationDispatcher(List<CardImplementation<?>> cardImplementations) {
        cardImplementationTypeMap = cardImplementations.stream()
            .collect(Collectors.groupingBy(CardImplementation::getType));
    }

    public boolean check(Card card, HttpServletRequest request) {
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
