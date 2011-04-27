package com.thoughtworks.itcoverage;

import org.junit.Ignore;

import java.io.File;
import java.net.URL;

@Ignore
public class BaseTestFinderTest {
    protected static final String STORY_STUB_TEST_PATTERN = ".*Story.*StubTest.*";

    protected File getTestSourceDir() {
        URL url = this.getClass().getClassLoader().getResource("stub.class");
        return new File(new File(url.getFile()).getParentFile().getParentFile().getParent() +
                File.separator + "src" +
                File.separator + "test" +
                File.separator + "java");
    }

    protected File getTestClassesDir() {
        URL url = this.getClass().getClassLoader().getResource("stub.class");
        return new File(url.getFile()).getParentFile();
    }
}
