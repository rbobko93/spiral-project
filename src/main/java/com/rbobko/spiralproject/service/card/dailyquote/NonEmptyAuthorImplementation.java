package com.rbobko.spiralproject.service.card.dailyquote;

import com.rbobko.spiralproject.model.DailyQuoteCard;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NonEmptyAuthorImplementation implements DailyQuoteCardImplementation {

    @Override
    public boolean check(DailyQuoteCard card) {
        log.debug("Running {} check on {}", this.getClass().getSimpleName(), card);
        return Objects.nonNull(card.getAuthor());
    }
}
