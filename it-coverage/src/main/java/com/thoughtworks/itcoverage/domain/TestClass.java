package com.thoughtworks.itcoverage.domain;

import java.util.ArrayList;
import java.util.List;

public class TestClass {
    private String name;
    private Integer storyNumber;
    private List<TestCase> testCases;

    public TestClass(String name, Integer storyNumber) {
        this.name = name;
        this.storyNumber = storyNumber;
        testCases = new ArrayList<TestCase>();
    }

    public String getName() {
        return name;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }

    public Integer getStoryNumber() {
        return storyNumber;
    }
}
