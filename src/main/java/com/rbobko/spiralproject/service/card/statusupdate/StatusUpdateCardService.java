package com.rbobko.spiralproject.service.card.statusupdate;

import com.rbobko.spiralproject.model.Card;
import com.rbobko.spiralproject.model.StatusUpdateCard;
import com.rbobko.spiralproject.service.card.CardFeedProvider;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class StatusUpdateCardService implements CardFeedProvider<StatusUpdateCard> {

    public StatusUpdateCardService() {}

    @Override
    @Transactional(readOnly = true)
    public List<Card> getCardFeed(HttpServletRequest request) {
        log.debug("Fetching StatusUpdate cards");
//        var statuses = statusService.findAll();
//
//        if (!statuses.isEmpty()) {
//            log.debug("Found {} Status updates", statuses.size());
//
//            return statuses.stream()
//                .map(statusMapper::toCard)
//                .filter(this::checkImplementations)
//                .collect(Collectors.toList());
//        }

        return Collections.emptyList();
    }

    @Override
    public boolean checkImplementations(StatusUpdateCard card) {
        return true;
    }
}
