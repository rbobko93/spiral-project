package com.rbobko.spiralproject.service.card.statusupdate;

import com.rbobko.spiralproject.model.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RequestTimestampImplementation extends StatusUpdateCardImplementation{

    @Override
    public boolean check(Card card) {
        var c = toSpecificCardClass(card);
        log.debug("Running {} check on {}", this.getClass().getSimpleName(), card);
        return true;
    }
}
