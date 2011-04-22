package com.thoughtworks.maven;

import org.junit.Ignore;

import java.io.File;
import java.net.URL;

@Ignore
public class BaseClassFinderTest {
    protected static final String STORY_STUB_TEST_PATTERN = ".*Story.*StubTest.*";

    protected File getTestClassesDir() {
        URL url = this.getClass().getClassLoader().getResource("Stub.class");
        return new File(url.getFile()).getParentFile();
    }
}
