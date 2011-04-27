package com.thoughtworks.itcoverage.domain;

import java.util.ArrayList;
import java.util.List;

public class StoryCoverage {

    private Story story;
    private Double coverage;
    private List<TestCase> testCases;

    public StoryCoverage(Story story) {
        this.story = story;
        testCases = new ArrayList<TestCase>();
    }

    public Story getStory() {
        return story;
    }

    public Double getCoverage() {
        return coverage;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void addTestCase(TestCase testCase) {
        this.testCases.add(testCase);
    }

    public void calculateCoverage() {
        if (story.hasAC()) {
            coverage = (double) testCases.size() / story.getAcceptanceCriteria().size();
        } else {
            coverage = 0.0;
        }
    }
}
