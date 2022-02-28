package com.rbobko.spiralproject.service.card.statusupdate;

import static org.assertj.core.api.Assertions.assertThat;

import com.rbobko.spiralproject.model.StatusUpdateCard;
import com.rbobko.spiralproject.repository.StatusUpdateCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class StatusUpdateCardServiceIT {

    @Autowired
    private StatusUpdateCardService statusUpdateCardService;

    @Autowired
    private StatusUpdateCardRepository statusUpdateCardRepository;

    private StatusUpdateCard c1;
    private StatusUpdateCard c2;

    @BeforeEach
    void beforeEach() {
        c1 = statusUpdateCardRepository.save(StatusUpdateCard.builder()
            .title("Security update")
            .message("Important message")
            .icon("iconurl")
            .button("nice button text")
            .build());

        c2 = statusUpdateCardRepository.save(StatusUpdateCard.builder()
            .title("Terms of service update")
            .message("Whatever")
            .icon("iconurl")
            .button("Got it")
            .build());
    }

    @Test
    void testGetCardFeed() {
        // When
        var result = statusUpdateCardService.getCardFeed();

        // Then
        assertThat(result).hasSize(2).contains(c1, c2);
    }

    @Test
    void testGetCardFeed_NoCards() {
        // Given
        assertThat(statusUpdateCardRepository.count()).isEqualTo(2);
        statusUpdateCardRepository.deleteAll();
        assertThat(statusUpdateCardRepository.count()).isZero();

        // When
        var result = statusUpdateCardService.getCardFeed();

        // Then
        assertThat(result).isEmpty();
    }

}
