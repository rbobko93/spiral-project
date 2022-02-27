package com.rbobko.spiralproject.service.card.dailyquote;

import com.rbobko.spiralproject.model.dto.DailyQuoteCard;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NonEmptyAuthorImplementation implements DailyQuoteCardImplementation {

    @Override
    public boolean check(DailyQuoteCard card) {
        log.debug("Running {} check on {}", this.getClass().getSimpleName(), card);
        return Strings.isNotEmpty(card.getAuthor());
    }
}
