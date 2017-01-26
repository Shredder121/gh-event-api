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
@lombok.experimental.UtilityClass
public class HamcrestHelpers {

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
    public <O, T> Matcher<O> property(Function<? super O, ? extends T> selector, Matcher<? super T> predicate, O... shim) {
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

    public interface BaxterAndTheHackers {

        Matcher<User> BAXTERANDTHEHACKERS = allOf(asList(
                property(User::getId, is(7649605)),
                property(User::getLogin, is("baxterandthehackers")),
                property(User::getHtmlUrl, is("https://github.com/baxterandthehackers"))
        ));

        Matcher<Organization> BAXTERANDTHEHACKERS_ORG = allOf(asList(
                property(Organization::getId, is(7649605)),
                property(Organization::getLogin, is("baxterandthehackers")),
                property(Organization::getUrl, is("https://api.github.com/orgs/baxterandthehackers"))
        ));

        Matcher<Repository> BAXTERANDTHEHACKERS_NEW_REPOSITORY = allOf(asList(
                property(Repository::getName, is("new-repository")),
                property(Repository::getFullName, is("baxterandthehackers/new-repository")),
                property(Repository::getOwner, is(BAXTERANDTHEHACKERS))
        ));

        Matcher<Repository> BAXTERANDTHEHACKERS_PUBLIC_REPO = allOf(asList(
                property(Repository::getName, is("public-repo")),
                property(Repository::getFullName, is("baxterandthehackers/public-repo")),
                property(Repository::getOwner, is(BAXTERANDTHEHACKERS))
        ));
    }

    public interface BaxterTheHacker {

        Matcher<User> BAXTERTHEHACKER = allOf(asList(
                property(User::getId, is(6752317)),
                property(User::getLogin, is("baxterthehacker")),
                property(User::getHtmlUrl, is("https://github.com/baxterthehacker"))
        ));

        Matcher<Repository> BAXTERTHEHACKER_PUBLIC_REPO = allOf(asList(
                property(Repository::getName, is("public-repo")),
                property(Repository::getFullName, is("baxterthehacker/public-repo")),
                property(Repository::getOwner, is(either(BAXTERTHEHACKER)
                        .or(anything(/*XXX BUG IN GITHUB*/))))
        ));
    }

    public interface JasonRudolph {

        Matcher<User> JASONRUDOLPH = allOf(asList(
                property(User::getId, is(2988)),
                property(User::getLogin, is("jasonrudolph")),
                property(User::getHtmlUrl, is("https://github.com/jasonrudolph"))
        ));
    }

    public interface Kdaigle {

        Matcher<User> KDAIGLE = allOf(asList(
                property(User::getId, is(2501)),
                property(User::getLogin, is("kdaigle")),
                property(User::getHtmlUrl, is("https://github.com/kdaigle"))
        ));
    }

    public interface Octocat {

        Matcher<User> OCTOCAT = allOf(asList(
                property(User::getId, is(583231)),
                property(User::getLogin, is("octocat")),
                property(User::getHtmlUrl, is("https://github.com/octocat"))
        ));
    }
}
