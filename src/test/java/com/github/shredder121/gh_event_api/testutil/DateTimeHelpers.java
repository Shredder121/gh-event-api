package com.github.shredder121.gh_event_api.testutil;

import java.time.*;

@lombok.experimental.UtilityClass
public class DateTimeHelpers {

    public ZonedDateTime dt(String date, String time) {
        return ZonedDateTime.of(d(date), t(time), ZoneId.ofOffset("UTC", ZoneOffset.UTC));
    }

    public LocalDate d(String date) {
        return LocalDate.parse(date);
    }

    public LocalTime t(String time) {
        return LocalTime.parse(time);
    }
}
