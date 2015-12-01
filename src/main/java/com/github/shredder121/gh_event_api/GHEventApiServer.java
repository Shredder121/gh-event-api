/*
 * Copyright 2015 Shredder121.
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
package com.github.shredder121.gh_event_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import com.google.common.collect.ObjectArrays;

/**
 * The starter class for the server.
 *
 * @author Shredder121
 */
@SpringBootApplication
public class GHEventApiServer {

    /**
     * The method to start the bare server.
     *
     * <p>
     * To make your {@link Component component}s known specify them under the {@code spring.main.sources } system property.
     * </p>
     *
     * Usage(add this to the configuration of the {@code spring-boot-maven-plugin}):
     * <pre>{@literal
     * <mainClass>com.github.shredder121.gh_event_api.GHEventApiServer</mainClass>
     * <arguments>
     *   <argument>--spring.main.sources=<YOUR.SOURCES.HERE></argument>
     * </arguments>
     * }</pre>
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(GHEventApiServer.class, args);
    }

    /**
     * The method to start your {@code app} under the server.
     *
     * @param app the app that will be included
     * @param args the optional (command-line) arguments
     * @return the running {@link ApplicationContext}
     */
    public static ConfigurableApplicationContext start(Class<?> app, String... args) {
        return start(new Class<?>[]{app}, args);
    }

    /**
     * The method to start multiple {@code app}s under the server.
     *
     * @param app multiple apps that will be included
     * @param args the optional (command-line) arguments
     * @return the running {@link ApplicationContext}
     */
    public static ConfigurableApplicationContext start(Class<?>[] app, String... args) {
        Class<?>[] apps = ObjectArrays.concat(app, GHEventApiServer.class);
        return SpringApplication.run(apps, args);
    }
}
