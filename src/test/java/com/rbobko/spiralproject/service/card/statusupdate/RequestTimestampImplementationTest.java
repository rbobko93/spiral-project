package com.rbobko.spiralproject.service.card.statusupdate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

class RequestTimestampImplementationTest {

    private final RequestTimestampImplementation requestTimestampImplementation = new RequestTimestampImplementation();

    @Test
    void testCheck_NoDate() {
        // Given
        var request = new MockHttpServletRequest();

        // When
        assertThat(requestTimestampImplementation.check(null, request)).isTrue();
    }

    @Test
    void testCheck() {
        // Given
        var request = new MockHttpServletRequest();
        var now = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        request.addHeader("date", now);

        // When
        assertThat(requestTimestampImplementation.check(null, request)).isEqualTo(now % 2 == 0);
    }

    @Test
    void testCheck_NullRequest() {
        // When
        assertThrows(IllegalArgumentException.class, () -> requestTimestampImplementation.check(null, null));
    }

}
