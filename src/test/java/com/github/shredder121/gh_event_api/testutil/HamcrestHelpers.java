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

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;

import java.util.function.Function;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import com.github.shredder121.gh_event_api.model.Organization;
import com.github.shredder121.gh_event_api.model.Repository;
import com.github.shredder121.gh_event_api.model.User;

/**
 * Helpers to get more out of Hamcrest's matchers.
 *
 * @author Shredder121
 */
public class HamcrestHelpers {

    private HamcrestHelpers() {
    }

    /**
     * Before matching via hamcrest, select a variable with a lambda expression.
     *
     * @param <O> original expression type
     * @param <T> new expression type
     * @param selector the selector to apply
     * @param predicate the next Hamcrest matcher to apply
     * @param shim to capture the runtime type of the Function's input, we make the compiler insert an array that holds
     * the right type
     * @return the composed matcher
     */
    @SafeVarargs
    public static <O, T> Matcher<O> property(Function<? super O, ? extends T> selector, Matcher<? super T> predicate, O... shim) {
        return new TypeSafeMatcher<O>(shim.getClass().getComponentType()) {
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

    public static class BaxterAndTheHackers {

        private BaxterAndTheHackers() {
        }

        public static Matcher<User> BAXTERANDTHEHACKERS = allOf(asList(
                property(User::getId, is(7649605)),
                property(User::getLogin, is("baxterandthehackers")),
                property(User::getHtmlUrl, is("https://github.com/baxterandthehackers"))
        ));

        public static Matcher<Organization> BAXTERANDTHEHACKERS_ORG = allOf(asList(
                property(Organization::getId, is(7649605)),
                property(Organization::getLogin, is("baxterandthehackers")),
                property(Organization::getUrl, is("https://api.github.com/orgs/baxterandthehackers"))
        ));

        public static Matcher<Repository> BAXTERANDTHEHACKERS_NEW_REPOSITORY = allOf(asList(
                property(Repository::getName, is("new-repository")),
                property(Repository::getFullName, is("baxterandthehackers/new-repository")),
                property(Repository::getOwner, is(BAXTERANDTHEHACKERS))
        ));

        public static Matcher<Repository> BAXTERANDTHEHACKERS_PUBLIC_REPO = allOf(asList(
                property(Repository::getName, is("public-repo")),
                property(Repository::getFullName, is("baxterandthehackers/public-repo")),
                property(Repository::getOwner, is(BAXTERANDTHEHACKERS))
        ));
    }

    public static class BaxterTheHacker {

        private BaxterTheHacker() {
        }

        public static Matcher<User> BAXTERTHEHACKER = allOf(asList(
                property(User::getId, is(6752317)),
                property(User::getLogin, is("baxterthehacker")),
                property(User::getHtmlUrl, is("https://github.com/baxterthehacker")),
                property(User::isSiteAdmin, is(false))
        ));

        public static Matcher<Repository> BAXTERTHEHACKER_PUBLIC_REPO = allOf(asList(
                property(Repository::getName, is("public-repo")),
                property(Repository::getFullName, is("baxterthehacker/public-repo")),
                property(Repository::getOwner, is(either(BAXTERTHEHACKER).or(anything(/*XXX BUG IN GITHUB*/))))
        ));
    }

    public static class JasonRudolph {

        private JasonRudolph() {
        }

        public static Matcher<User> JASONRUDOLPH = allOf(asList(
                property(User::getId, is(2988)),
                property(User::getLogin, is("jasonrudolph")),
                property(User::getHtmlUrl, is("https://github.com/jasonrudolph")),
                property(User::isSiteAdmin, is(true))
        ));
    }

    public static class Kdaigle {

        private Kdaigle() {
        }

        public static Matcher<User> KDAIGLE = allOf(asList(
                property(User::getId, is(2501)),
                property(User::getLogin, is("kdaigle")),
                property(User::getHtmlUrl, is("https://github.com/kdaigle")),
                property(User::isSiteAdmin, is(true))
        ));
    }

    public static class Octocat {

        private Octocat() {
        }

        public static Matcher<User> OCTOCAT = allOf(asList(
                property(User::getId, is(583231)),
                property(User::getLogin, is("octocat")),
                property(User::getHtmlUrl, is("https://github.com/octocat"))
        ));
    }
}
