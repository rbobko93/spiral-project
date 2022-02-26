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
public class DailyQuoteCardService implements CardFeedProvider {

    private static final String CARD_TITLE = "Daily Quote";

    private final QuoteService quoteService;

    public DailyQuoteCardService(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @Transactional(readOnly = true)
    public List<Card> getCardFeed(final HttpServletRequest request) {
        LocalDate today = LocalDate.now(ZoneOffset.UTC);
        log.debug("Generating DailyQuoteCard for {}", today);

        var quoteOpt = quoteService.findByDate(today);

        if (quoteOpt.isPresent()) {
            var quote = quoteOpt.get();
            var dailyCard = DailyQuoteCard.builder()
                .title(CARD_TITLE)
                .message(quote.getMessage())
                .author(quote.getAuthor()).build();

            return List.of(dailyCard);
        }

        return Collections.emptyList();
    }
}
