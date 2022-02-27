package com.rbobko.spiralproject.service.card.dailyquote;

import com.rbobko.spiralproject.model.Card;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QuoteLengthImplementation extends DailyQuoteCardImplementation {

    @Override
    public boolean check(Card card, HttpServletRequest request) {
        var c = toSpecificCardClass(card);
        log.debug("Running {} check on {}", this.getClass().getSimpleName(), card);
        return c.getMessage().length() > 3;
    }
}
