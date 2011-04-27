package com.thoughtworks.itcoverage;

import com.thoughtworks.itcoverage.domain.TestCase;
import com.thoughtworks.itcoverage.domain.TestClass;
import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.DocletTag;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestFinder {

    private static final String STORY_TAG_NAME = "story";
    private static final String TEST_METHOD_PATTERN = "test.*";
    File dir;
    String pattern;

    public TestFinder(File dir, String pattern) {
        this.dir = dir;
        this.pattern = pattern;
    }

    public List<TestClass> findAllByJavaDoc() {
        List<TestClass> testClasses = new ArrayList<TestClass>();

        JavaDocBuilder builder = new JavaDocBuilder();
        builder.setEncoding("UTF-8");
        builder.addSourceTree(dir);
        for (JavaClass c : builder.getClasses()) {
            if (isTestClass(c)) {
                TestClass testClass = new TestClass(c.getName(), getStoryNumber(c.getTagByName(STORY_TAG_NAME)));
                for (JavaMethod m : c.getMethods()) {
                    if (isTestCase(m)) {
                        testClass.addTestCase(
                                new TestCase(m.getName(), getStoryNumber(m.getTagByName(STORY_TAG_NAME)), testClass));
                    }
                }
                testClasses.add(testClass);
            }
        }
        return testClasses;
    }

    private Integer getStoryNumber(DocletTag story) {
        if (story == null) {
            return null;
        }
        try {
            return Integer.parseInt(story.getValue());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private boolean isTestClass(JavaClass c) {
        return c.getName().matches(pattern);
    }

    private boolean isTestCase(JavaMethod m) {
        return m.getName().matches(TEST_METHOD_PATTERN);
    }

}
