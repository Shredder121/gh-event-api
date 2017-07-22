package com.github.shredder121.gh_event_api.testutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * An additional module that configures printing a warning about properties that exist in JSON form, but not in the source.
 *
 * @author Shredder121
 */
@Component
@SuppressWarnings("serial") // Interoperability is not required
class GHEventApiTestModule extends SimpleModule {

    public GHEventApiTestModule() {
        super("GHEventApiServerTest");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.addDeserializationProblemHandler(new WarningDeserializationProblemHandler());
    }

    static class WarningDeserializationProblemHandler extends DeserializationProblemHandler {

        static Logger logger = LoggerFactory.getLogger(WarningDeserializationProblemHandler.class);

        @Override
        public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser jp, JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) {
            logger.debug("Unhandled field {}.{}", beanOrClass.getClass(), propertyName);
            return false;
        }
    }
}
