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
package com.github.shredder121.gh_event_api.model;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.NonFinal;

@JsonTest
@RunWith(SpringRunner.class)
@lombok.RequiredArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public abstract class AbstractSerializationTest<T> {

    @Rule
    public ErrorCollector errorCollector = new ErrorCollector();

    Class<T> model;

    @Autowired
    @NonFinal ObjectMapper mapper;

    @Test
    public final void deserialize() throws Exception {
        T deserialized = doDeserialize();

        // force coverage
        deserialized.equals(doDeserialize());
        deserialized.hashCode();
        deserialized.toString();

        checkDeserialized(deserialized);
    }

    private T doDeserialize() throws IOException {
        return mapper.reader()
                .forType(model)
                .readValue(getSample());
    }

    protected abstract String getSample();

    protected abstract void checkDeserialized(T deserialized);
}
