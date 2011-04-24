package com.thoughtworks.itcoverage;

import com.thoughtworks.itcoverage.annotation.Story;
import com.thoughtworks.itcoverage.domain.TestCase;
import com.thoughtworks.itcoverage.domain.TestClass;
import org.junit.Test;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class TestFinder {

    File dir;
    String pattern;

    public TestFinder(File dir, String pattern) {
        this.dir = dir;
        this.pattern = pattern;
    }

    public List<TestClass> findAll() throws MalformedURLException {
        ClassFinder classFinder = new ClassFinder();
        List<Class> classes = classFinder.findAll(dir, pattern);

        List<TestClass> testClasses = new ArrayList<TestClass>();
        for (Class c : classes) {
            TestClass testClass = new TestClass(getLastClassName(c), getTestClassStoryNumber(c));

            Method[] methods = c.getDeclaredMethods();
            for (Method m : methods) {
                if (isTestCase(m)) {
                    testClass.addTestCase(new TestCase(m.getName(), getMethodStoryNumber(m), testClass));
                }
            }
            testClasses.add(testClass);
        }

        return testClasses;
    }

    private String getLastClassName(Class c) {
        int lastDotPosition = c.getName().lastIndexOf(".");
        if (lastDotPosition > 0) {
            return c.getName().substring(lastDotPosition + 1);
        }
        return c.getName();
    }

    private Integer getMethodStoryNumber(Method m) {
        Story annotation = m.getAnnotation(Story.class);
        if (annotation != null) {
            return annotation.value();
        }
        return null;
    }

    private Integer getTestClassStoryNumber(Class c) {
        Annotation annotation = c.getAnnotation(Story.class);
        if (annotation instanceof Story) {
            return ((Story) annotation).value();
        }
        return null;
    }

    private boolean isTestCase(Method m) {
        Test methodAnnotation = m.getAnnotation(Test.class);
        return methodAnnotation != null;
    }
}
