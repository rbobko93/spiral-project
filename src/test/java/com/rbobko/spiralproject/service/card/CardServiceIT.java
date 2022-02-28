package com.rbobko.spiralproject.service.card;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.rbobko.spiralproject.model.DailyQuoteCard;
import com.rbobko.spiralproject.model.StatusUpdateCard;
import com.rbobko.spiralproject.repository.DailyQuoteCardRepository;
import com.rbobko.spiralproject.repository.StatusUpdateCardRepository;
import com.rbobko.spiralproject.service.card.dailyquote.NonEmptyAuthorImplementation;
import com.rbobko.spiralproject.service.card.dailyquote.QuoteLengthImplementation;
import com.rbobko.spiralproject.service.card.statusupdate.RequestTimestampImplementation;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.mock.web.MockHttpServletRequest;

@SpringBootTest
class CardServiceIT {

    @Autowired
    private CardService cardService;

    @SpyBean
    private NonEmptyAuthorImplementation nonEmptyAuthorImplementation;

    @SpyBean
    private QuoteLengthImplementation quoteLengthImplementation;

    @SpyBean
    private RequestTimestampImplementation requestTimestampImplementation;

    @Autowired
    private DailyQuoteCardRepository dailyQuoteCardRepository;

    @Autowired
    private StatusUpdateCardRepository statusUpdateCardRepository;

    @Test
    void testGetCardsFeed() {
        // Given
        var dailyCard = dailyQuoteCardRepository.save(DailyQuoteCard.builder()
            .author("author1")
            .message("quote1").date(LocalDate.now(ZoneOffset.UTC))
            .build());

        var statusUpdate = statusUpdateCardRepository.save(StatusUpdateCard.builder()
            .title("Status update")
            .message("important update")
            .icon("iconurl")
            .button("Got it")
            .build());

        var httpReq = new MockHttpServletRequest();

        // When
        var result = cardService.getCardsFeed(httpReq);

        // Then
        verify(nonEmptyAuthorImplementation).check(dailyCard, httpReq);
        verify(quoteLengthImplementation).check(dailyCard, httpReq);
        verify(requestTimestampImplementation).check(statusUpdate, httpReq);

        assertThat(result).hasSize(2).contains(dailyCard, statusUpdate);
    }


    @Test
    void testGetCardsFeed_NoCards() {
        // Given
        var httpReq = new MockHttpServletRequest();

        // When
        var result = cardService.getCardsFeed(httpReq);

        // Then
        verify(nonEmptyAuthorImplementation, times(0)).check(any(), any());
        verify(quoteLengthImplementation, times(0)).check(any(), any());
        verify(requestTimestampImplementation, times(0)).check(any(), any());

        assertThat(result).isEmpty();
    }

}
