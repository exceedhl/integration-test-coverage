package com.thoughtworks.maven.domain;

public class TestCase {
    private String name;
    private Integer storyNumber;
    private TestClass testClass;

    public TestCase(String name, Integer storyNumber, TestClass testClass) {
        this.name = name;
        this.storyNumber = storyNumber;
        this.testClass = testClass;
    }

    public String getName() {
        return name;
    }

    public Integer getStoryNumber() {
        return storyNumber;
    }

    public TestClass getTestClass() {
        return testClass;
    }
}
