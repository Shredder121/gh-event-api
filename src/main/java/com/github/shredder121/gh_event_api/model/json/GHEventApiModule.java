/*
 * Copyright 2016 Shredder121.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.shredder121.gh_event_api.model.json;

import java.util.function.Function;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

/**
 * The Jackson Module that configures the way Jackson should deserialize classes.
 *
 * @author Shredder121
 */
@Component
@SuppressWarnings("serial") // Interoperability is not required
class GHEventApiModule extends SimpleModule {

    public GHEventApiModule() {
        super("GHEventApiServer");

        addDeserializer(MediaType.class, new StdDelegatingDeserializer<>(fromString(MediaType::valueOf)));
    }

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
        // https://github.com/spring-projects/spring-boot/issues/4490
        new GuavaModule().setupModule(context);
    }

    private static <T> Converter<String, T> fromString(Function<String, ? extends T> fun) {
        return new StdConverter<String, T>() {

            @Override
            public T convert(String value) {
                return fun.apply(value);
            }
        };
    }
}
