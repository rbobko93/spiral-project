package com.rbobko.spiralproject.service.card.dailyquote;

import com.rbobko.spiralproject.model.Card;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Slf4j
public class QuoteLengthImplementation extends DailyQuoteCardImplementation {

    @Override
    public boolean check(Card card, HttpServletRequest request) {
        Assert.notNull(card, "Cannot run check on null value");
        var c = toSpecificCardClass(card);
        log.debug("Running {} check on {}", this.getClass().getSimpleName(), card);
        if (Objects.nonNull(c.getMessage())) {
            return c.getMessage().length() > 3;
        }

        return false;
    }
}
