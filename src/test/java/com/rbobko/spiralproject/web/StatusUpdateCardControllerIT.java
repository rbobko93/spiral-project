package com.rbobko.spiralproject.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbobko.spiralproject.model.CardType;
import com.rbobko.spiralproject.model.StatusUpdateCard;
import com.rbobko.spiralproject.model.dto.StatusUpdateCardUpdateDTO;
import com.rbobko.spiralproject.repository.StatusUpdateCardRepository;
import com.rbobko.spiralproject.web.exception.GlobalExceptionHandler;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class StatusUpdateCardControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private StatusUpdateCardRepository statusUpdateCardRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private StatusUpdateCardController statusUpdateCardController;

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void beforeEach() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(statusUpdateCardController)
            .setControllerAdvice(globalExceptionHandler)
            .build();
    }

    @Test
    void testGetById() throws Exception {
        // Given
        var card = statusUpdateCardRepository.save(StatusUpdateCard.builder()
            .title("t1")
            .message("m1")
            .icon("i1")
            .button("b1")
            .build());

        // When
        var result = this.mockMvc.perform(get("/api/status-update-cards/{id}", card.getId()))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        // Then
        var c = mapper.readValue(result.getResponse().getContentAsString(), StatusUpdateCard.class);

        assertThat(c).isNotNull();
        assertThat(c.getId()).isEqualTo(card.getId());
        assertThat(c.getTitle()).isEqualTo(card.getTitle());
        assertThat(c.getMessage()).isEqualTo(card.getMessage());
        assertThat(c.getIcon()).isEqualTo(card.getIcon());
        assertThat(c.getButton()).isEqualTo(card.getButton());
        assertThat(c.getType()).isEqualTo(CardType.STATUS_UPDATE);
    }

    @Test
    void testGetById_NotFound() throws Exception {
        // When
        this.mockMvc.perform(get("/api/status-update-cards/{id}", 123L))
            .andExpect(status().isNotFound());
    }

    @Test
    void testGetAll() throws Exception {
        // Given
        var c1 = statusUpdateCardRepository.save(StatusUpdateCard.builder()
            .title("t1")
            .message("m1")
            .icon("i1")
            .button("b1")
            .build());

        var c2 = statusUpdateCardRepository.save(StatusUpdateCard.builder()
            .title("t2")
            .message("m2")
            .icon("i2")
            .button("b2")
            .build());

        // When
        var result = this.mockMvc.perform(get("/api/status-update-cards"))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        // Then
        var cardList = mapper.readValue(result.getResponse().getContentAsString(),
            new TypeReference<List<StatusUpdateCard>>() {
            });

        assertThat(cardList).hasSize(2).contains(c1, c2);
    }

    @Test
    void testGetAll_Empty() throws Exception {
        // When
        this.mockMvc.perform(get("/api/status-update-cards"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testCreateCard() throws Exception {
        // Given
        assertThat(statusUpdateCardRepository.count()).isZero();
        var dto = StatusUpdateCardUpdateDTO.builder()
            .title("t1")
            .message("m1")
            .icon("i1")
            .button("b1")
            .build();

        // When
        var result = this.mockMvc.perform(post("/api/status-update-cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(dto)))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        // Then
        assertThat(statusUpdateCardRepository.count()).isEqualTo(1);
        var created = mapper.readValue(result.getResponse().getContentAsString(), StatusUpdateCard.class);
        assertThat(created.getId()).isNotNull();
        assertThat(created.getTitle()).isEqualTo(dto.getTitle());
        assertThat(created.getMessage()).isEqualTo(dto.getMessage());
        assertThat(created.getIcon()).isEqualTo(dto.getIcon());
        assertThat(created.getButton()).isEqualTo(dto.getButton());
        assertThat(created.getType()).isEqualTo(CardType.STATUS_UPDATE);
    }

    @Test
    void testCreateCard_Invalid() throws Exception {
        // Given
        var dto = StatusUpdateCardUpdateDTO.builder()
            .title("t1")
            .message("m1")
            .button("b1")
            .build();

        // When
        this.mockMvc.perform(post("/api/status-update-cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(dto)))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateCard_IncludingId() throws Exception {
        // Given
        var dto = StatusUpdateCardUpdateDTO.builder()
            .id(1L)
            .title("t1")
            .icon("i1")
            .message("m1")
            .button("b1")
            .build();

        // When
        this.mockMvc.perform(post("/api/status-update-cards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(dto)))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdate() throws Exception {
        // Given
        var card = statusUpdateCardRepository.save(StatusUpdateCard.builder()
            .message("m1")
            .title("t1")
            .icon("i1")
            .button("b1")
            .build());

        var dto = StatusUpdateCardUpdateDTO.builder()
            .id(card.getId())
            .message("m2")
            .title("t2")
            .icon("i2")
            .button("b2")
            .build();

        // When
        var result = this.mockMvc.perform(put("/api/status-update-cards/{id}", card.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(dto)))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        // Then
        var c = mapper.readValue(result.getResponse().getContentAsString(), StatusUpdateCard.class);
        assertThat(c.getId()).isEqualTo(card.getId());
        assertThat(c.getMessage()).isEqualTo(dto.getMessage());
        assertThat(c.getTitle()).isEqualTo(dto.getTitle());
        assertThat(c.getIcon()).isEqualTo(dto.getIcon());
        assertThat(c.getButton()).isEqualTo(dto.getButton());

        var repoCard = statusUpdateCardRepository.findById(card.getId()).orElse(null);
        assertThat(repoCard).isNotNull();
        assertThat(repoCard.getMessage()).isEqualTo(dto.getMessage());
    }

    @Test
    void testDeleteById() throws Exception {
        // Given
        var card = statusUpdateCardRepository.saveAndFlush(StatusUpdateCard.builder()
            .message("m1")
            .title("t1")
            .icon("i1")
            .button("b1")
            .build());
        assertThat(statusUpdateCardRepository.count()).isEqualTo(1);

        // When
        this.mockMvc.perform(delete("/api/status-update-cards/{id}", card.getId()))
            .andExpect(status().isNoContent());

        // Then
        assertThat(statusUpdateCardRepository.count()).isZero();
    }

    @Test
    void testDeleteById_NotFound() throws Exception {
        // When
        this.mockMvc.perform(delete("/api/status-update-cards/123"))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

}
