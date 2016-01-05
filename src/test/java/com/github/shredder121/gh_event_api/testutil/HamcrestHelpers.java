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
package com.github.shredder121.gh_event_api.testutil;

import java.util.function.Function;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

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
        return new TypeSafeMatcher<O>() {
            @Override
            public boolean matchesSafely(O original) {
                return predicate.matches(selector.apply(original));
            }

            @Override
            public void describeTo(Description description) {
                description.appendDescriptionOf(predicate);
            }

            @Override
            public void describeMismatchSafely(O original, Description description) {
                predicate.describeMismatch(selector.apply(original), description);
            }
        };
    }
}
