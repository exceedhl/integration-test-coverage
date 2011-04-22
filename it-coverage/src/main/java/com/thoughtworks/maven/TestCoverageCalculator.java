package com.thoughtworks.maven;

import com.thoughtworks.maven.domain.*;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

public class TestCoverageCalculator {
    private StoryFinder storyFinder;
    private TestFinder testFinder;

    public TestCoverageCalculator(StoryFinder storyFinder, TestFinder testFinder) {
        this.storyFinder = storyFinder;
        this.testFinder = testFinder;
    }

    public CoverageResult calculate() throws MalformedURLException {
        StoryList stories = storyFinder.findAll();

        CoverageResult coverageResult = new CoverageResult();

        HashMap<Integer, StoryCoverage> map = new HashMap<Integer, StoryCoverage>();
        for (Story story : stories.getStories()) {
            map.put(story.getNumber(), new StoryCoverage(story));
        }

        findTestCasesForStory(coverageResult, map);

        for (Integer key : map.keySet()) {
            StoryCoverage storyCoverage = map.get(key);
            storyCoverage.calculateCoverage();
            coverageResult.addStoryCovereage(storyCoverage);
        }

        return coverageResult;
    }

    private void findTestCasesForStory(CoverageResult coverageResult, HashMap<Integer, StoryCoverage> map) throws MalformedURLException {
        for (TestClass testClass : testFinder.findAll()) {
            for (TestCase testCase : testClass.getTestCases()) {
                Integer storyNumber = getStoryNumber(testCase);
                if (map.containsKey(storyNumber)) {
                    map.get(storyNumber).addTestCase(testCase);
                } else {
                    coverageResult.addIndependantTestCase(testCase);
                }
            }
        }
    }

    private Integer getStoryNumber(TestCase testCase) {
        if (testCase.getStoryNumber() != null) {
            return testCase.getStoryNumber();
        }
        return testCase.getTestClass().getStoryNumber();
    }
}
