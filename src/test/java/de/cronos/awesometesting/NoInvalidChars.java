package de.cronos.awesometesting;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class NoInvalidChars extends TypeSafeMatcher<String> {

    @Override
    protected boolean matchesSafely(String s) {
        return s.contains("#") ? false : true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("# not allowed");
    }

    public static Matcher<String> noInvalidChars() {
        return new NoInvalidChars();
    }
}
