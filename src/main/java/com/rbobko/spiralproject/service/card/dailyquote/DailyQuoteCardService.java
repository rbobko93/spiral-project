package com.rbobko.spiralproject.service.card.dailyquote;

import com.rbobko.spiralproject.mapper.QuoteMapper;
import com.rbobko.spiralproject.model.dto.Card;
import com.rbobko.spiralproject.model.dto.DailyQuoteCard;
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
    private final QuoteMapper quoteMapper;

    public DailyQuoteCardService(QuoteService quoteService,
        List<DailyQuoteCardImplementation> dailyQuoteCardImplementations, QuoteMapper quoteMapper) {
        this.quoteService = quoteService;
        this.dailyQuoteCardImplementations = dailyQuoteCardImplementations;
        this.quoteMapper = quoteMapper;
    }

    @Transactional(readOnly = true)
    public List<Card> getCardFeed(final HttpServletRequest request) {
        LocalDate today = LocalDate.now(ZoneOffset.UTC);
        log.debug("Fetching DailyQuoteCard for {}", today);

        var quoteOpt = quoteService.findByDate(today);

        if (quoteOpt.isPresent()) {
            log.debug("Found Quote for {}. Generating DailyQuoteCard", today);
            var quote = quoteOpt.get();
            var dailyCard = quoteMapper.toCard(quote)
                .toBuilder().title(CARD_TITLE).build();

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
                log.debug("One of the implementations failed for {}", card);
                return false;
            }
        }

        return true;
    }

}
