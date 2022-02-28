package com.rbobko.spiralproject.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbobko.spiralproject.model.Card;
import com.rbobko.spiralproject.model.CardType;
import com.rbobko.spiralproject.model.DailyQuoteCard;
import com.rbobko.spiralproject.model.StatusUpdateCard;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
class CardControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EntityManager em;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @Transactional
    void testGetCardsFeed_NoCards() throws Exception {
        // When
        this.mvc.perform(get("/api/cards/feed"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @Transactional
    void testGetCardsFeed() throws Exception {
        // Given
        var dailyCard = DailyQuoteCard.builder()
            .date(LocalDate.now(ZoneOffset.UTC))
            .message("q123")
            .author("a1")
            .build();
        var statusCard = StatusUpdateCard.builder()
            .title("t1")
            .message("m1")
            .icon("i1")
            .button("b1")
            .build();

        em.persist(dailyCard);
        em.persist(statusCard);
        em.flush();

        // When
        var result = this.mvc.perform(get("/api/cards/feed"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        // Then
        var cardList = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Card>>() {
        });

        assertThat(cardList).hasSize(2);

        var dc = (DailyQuoteCard) cardList.stream().filter(c -> c.getType().equals(CardType.DAILY_QUOTE)).findFirst().orElse(null);
        var su = (StatusUpdateCard) cardList.stream().filter(c -> c.getType().equals(CardType.STATUS_UPDATE)).findFirst().orElse(null);

        assertThat(dc).isNotNull();
        assertThat(dc.getMessage()).isEqualTo(dailyCard.getMessage());
        assertThat(dc.getAuthor()).isEqualTo(dailyCard.getAuthor());
        assertThat(dc.getDate()).isEqualTo(dailyCard.getDate());

        assertThat(su).isNotNull();
        assertThat(su.getTitle()).isEqualTo(statusCard.getTitle());
        assertThat(su.getMessage()).isEqualTo(statusCard.getMessage());
        assertThat(su.getIcon()).isEqualTo(statusCard.getIcon());
        assertThat(su.getButton()).isEqualTo(statusCard.getButton());
    }
}
