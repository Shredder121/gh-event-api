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
package com.github.shredder121.gh_event_api.testutil;

import java.util.function.Function;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Helpers to get more out of Hamcrest's matchers.
 *
 * @author Shredder121
 */
public class HamcrestHelpers {

    /**
     * Before matching via hamcrest, select a variable with a lambda expression.
     *
     * @param <O> original expression type
     * @param <T> new expression type
     * @param selector the selector to apply
     * @param predicate the next Hamcrest matcher to apply
     * @return the composed matcher
     */
    public static <O, T> Matcher<O> property(Function<? super O, ? extends T> selector, Matcher<? super T> predicate) {
        return new BaseMatcher<O>() {
            @Override
            public boolean matches(Object item) {
                @SuppressWarnings("unchecked") // should be valid, client code otherwise wouldn't compile
                O original = (O) item;
                return predicate.matches(selector.apply(original));
            }

            @Override
            public void describeTo(Description description) {
                description.appendDescriptionOf(predicate);
            }

            @Override
            public void describeMismatch(Object item, Description description) {
                @SuppressWarnings("unchecked") // is not really valid, but is safe enough
                O original = (O) item;
                predicate.describeMismatch(selector.apply(original), description);
            }
        };
    }
}
