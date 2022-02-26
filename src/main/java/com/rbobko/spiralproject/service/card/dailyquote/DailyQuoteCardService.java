package com.rbobko.spiralproject.service.card.dailyquote;

import com.rbobko.spiralproject.model.Card;
import com.rbobko.spiralproject.model.DailyQuoteCard;
import com.rbobko.spiralproject.service.QuoteService;
import com.rbobko.spiralproject.service.card.CardFeedProvider;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class DailyQuoteCardService implements CardFeedProvider<DailyQuoteCard> {

    private static final String CARD_TITLE = "Daily Quote";

    private final QuoteService quoteService;
    private final List<DailyQuoteCardImplementation> dailyQuoteCardImplementations;

    public DailyQuoteCardService(QuoteService quoteService,
        List<DailyQuoteCardImplementation> dailyQuoteCardImplementations) {
        this.quoteService = quoteService;
        this.dailyQuoteCardImplementations = dailyQuoteCardImplementations;
    }

    @Transactional(readOnly = true)
    public List<Card> getCardFeed(final HttpServletRequest request) {
        LocalDate today = LocalDate.now(ZoneOffset.UTC);
        log.debug("Fetching DailyQuoteCard for {}", today);

        var quoteOpt = quoteService.findByDate(today);

        if (quoteOpt.isPresent()) {
            log.debug("Found Quote for {}. Generating DailyQuoteCard", today);
            var quote = quoteOpt.get();
            // todo convert to mapstruct mapper
            var dailyCard = DailyQuoteCard.builder()
                .title(CARD_TITLE)
                .message(quote.getMessage())
                .author(quote.getAuthor()).build();

            if (checkImplementations(dailyCard)) {
                return List.of(dailyCard);
            }
        }

        return Collections.emptyList();
    }

    public boolean checkImplementations(DailyQuoteCard card) {
        log.debug("Checking implementations for {}", card);
        for (DailyQuoteCardImplementation dailyQuoteCardImplementation : dailyQuoteCardImplementations) {
            if (!dailyQuoteCardImplementation.check(card)) {
                log.debug("One of the implementations failed. Skipping card: {}", card);
                return false;
            }
        }

        return true;
    }

}
