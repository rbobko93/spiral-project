package com.rbobko.spiralproject.service.card.dailyquote;

import com.rbobko.spiralproject.model.DailyQuoteCard;
import com.rbobko.spiralproject.repository.DailyQuoteCardRepository;
import com.rbobko.spiralproject.service.card.CardFeedProvider;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class DailyQuoteCardService implements CardFeedProvider<DailyQuoteCard> {

    public static final String CARD_TITLE = "Daily Quote";

    private final DailyQuoteCardRepository dailyQuoteCardRepository;

    public DailyQuoteCardService(
        DailyQuoteCardRepository dailyQuoteCardRepository) {
        this.dailyQuoteCardRepository = dailyQuoteCardRepository;
    }

    public Optional<DailyQuoteCard> findById(final Long id) {
        return dailyQuoteCardRepository.findById(id);
    }

    public List<DailyQuoteCard> findAll() {
        return dailyQuoteCardRepository.findAll();
    }

    public DailyQuoteCard save(final DailyQuoteCard dailyQuoteCard) {
        dailyQuoteCard.setTitle(CARD_TITLE);
        return dailyQuoteCardRepository.save(dailyQuoteCard);
    }

    public void deleteById(final Long id) {
        dailyQuoteCardRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<DailyQuoteCard> getCardFeed(final HttpServletRequest request) {
        LocalDate today = LocalDate.now(ZoneOffset.UTC);
        log.debug("Fetching DailyQuoteCard for {}", today);

        var quoteCards = dailyQuoteCardRepository.findByDate(today);

        return quoteCards.stream().findFirst().map(List::of).orElse(Collections.emptyList());
    }

}
