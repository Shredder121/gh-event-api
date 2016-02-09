# gh-event-api [![Build Status][]][Travis] [![Coverage Status][]][Coveralls]
GitHub Event API Server

## What it does

```java
@SpringBootApplication // You can make a regular Spring Boot application
public class Application {

    public static void main(String... args) {
        // This starts your app alongside gh-event-api
        GHEventApiServer.start(Application.class, args);
    }

    @Bean // Declaring your handlers as Spring beans will bootstrap them
    public PushHandler pushHandler() {
        return payload -> {
            System.out.println("got payload: " + payload);
        };
    }

    @Bean // You can declare as many handlers as you want/need
    public PushHandler anotherPushHandler() {
        return payload -> {
            System.out.println("got payload: " + payload);
        };
    }

}
```

After bootstrapping, the server listens for [GitHub events][] at the configured port.
(With sane defaults, courtesy of Spring Boot)

## Configuration

Since eventually it's a Spring Boot app, all [common application properties][] for the included version apply.

## Examples

There are a few reference implementations of GitHub Event API style webhooks.

[GitHub Review Window][] which is operational, right now mainly on this repository.

[SpringIssueMaster][] which is a POC, based on the behavior of the [Spring Issuemaster user][].



[Build Status]: https://travis-ci.org/Shredder121/gh-event-api.svg?branch=master
[common application properties]: http://docs.spring.io/spring-boot/docs/1.3.2.RELEASE/reference/html/common-application-properties.html
[Coveralls]: https://coveralls.io/github/Shredder121/gh-event-api?branch=master
[Coverage Status]: https://coveralls.io/repos/Shredder121/gh-event-api/badge.svg?branch=master&service=github
[GitHub events]: https://developer.github.com/webhooks/#events
[GitHub Review Window]: https://github.com/querydsl/gh-review-window
[Spring Issuemaster user]: https://github.com/spring-issuemaster
[SpringIssueMaster]: https://github.com/Shredder121/SpringIssueMaster
[Travis]: https://travis-ci.org/Shredder121/gh-event-api
