package com.github.shredder121.gh_event_api.handler.watch;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.shredder121.gh_event_api.handler.AbstractEndpointController;

@RestController
@RequestMapping(method = POST, headers = "X-GitHub-Event=watch")
@ConditionalOnBean(WatchHandler.class)
public class WatchEndpointController extends AbstractEndpointController<WatchHandler, WatchPayload> {

    @Autowired
    public WatchEndpointController(Collection<? extends WatchHandler> beans) {
        super(beans);
    }

    @Override
    protected Runnable runnableHandler(WatchHandler handler, WatchPayload payload) {
        return () -> handler.handle(payload);
    }
}
