package com.rbobko.spiralproject.service.card.statusupdate;

import com.rbobko.spiralproject.model.StatusUpdateCard;
import com.rbobko.spiralproject.repository.StatusUpdateCardRepository;
import com.rbobko.spiralproject.service.card.CardFeedProvider;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class StatusUpdateCardService implements CardFeedProvider<StatusUpdateCard> {

    private final StatusUpdateCardRepository statusUpdateCardRepository;

    public StatusUpdateCardService(StatusUpdateCardRepository statusUpdateCardRepository) {
        this.statusUpdateCardRepository = statusUpdateCardRepository;
    }

    public Optional<StatusUpdateCard> findById(final Long id) {
        return statusUpdateCardRepository.findById(id);
    }

    public List<StatusUpdateCard> findAll() {
        return statusUpdateCardRepository.findAll();
    }

    public StatusUpdateCard save(final StatusUpdateCard statusUpdateCard) {
        return statusUpdateCardRepository.save(statusUpdateCard);
    }

    public void deleteById(final Long id) {
        statusUpdateCardRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StatusUpdateCard> getCardFeed() {
        log.debug("Fetching StatusUpdate cards");
        return findAll();
    }
}
