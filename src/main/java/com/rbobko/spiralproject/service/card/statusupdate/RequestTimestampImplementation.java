package com.rbobko.spiralproject.service.card.statusupdate;

import com.rbobko.spiralproject.model.Card;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Slf4j
public class RequestTimestampImplementation extends StatusUpdateCardImplementation {

    @Override
    public boolean check(Card card, HttpServletRequest request) {
        Assert.notNull(request, "Cannot run check on null value");
        var timestamp = Long.valueOf(request.getDateHeader("date"));
        log.debug("Running {} check on {}", this.getClass().getSimpleName(), card);

        if (timestamp != -1) {
            return timestamp % 2 == 0;
        }

        return true;
    }
}
