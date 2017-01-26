package com.github.shredder121.gh_event_api.testutil;

import java.lang.annotation.*;

import org.springframework.test.context.ActiveProfiles;

@Documented
@ActiveProfiles("hmac")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface HmacTest {
}
