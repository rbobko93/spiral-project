package com.rbobko.spiralproject.web;

import com.rbobko.spiralproject.model.Card;
import com.rbobko.spiralproject.service.CardService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
@Slf4j
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/feed")
    public ResponseEntity<List<Card>> getCardsFeed() {
        log.debug("REST request to get Cards feed");
        return ResponseEntity.ok(cardService.getCardsFeed());
    }

}
