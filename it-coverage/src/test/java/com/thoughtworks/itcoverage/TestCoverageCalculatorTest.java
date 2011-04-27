package com.thoughtworks.itcoverage;

import com.thoughtworks.itcoverage.domain.CoverageResult;
import com.thoughtworks.itcoverage.domain.StoryCoverage;
import org.junit.Test;

import java.net.MalformedURLException;

import static junit.framework.Assert.assertEquals;

public class TestCoverageCalculatorTest extends BaseTestFinderTest {
    @Test
    public void shouldCalculateTestCoverageForAllStories() throws MalformedURLException {
        StoryFinder storyFinder = new StoryFinder("http://localhost:5555/storiesWithAC.xml", "whatever", "whatever",
                new ACParser());
        TestCoverageCalculator calculator = new TestCoverageCalculator(storyFinder,
                new TestFinder(getTestSourceDir(), STORY_STUB_TEST_PATTERN));
        CoverageResult result = calculator.calculate();
        assertEquals(2, result.getStoryCoverages().size());
        assertEquals(1, result.getIndependtantTestCases().size());
        assertEquals(0.5, result.getAverageCoverage());

        StoryCoverage storyCoverage1 = result.getStoryCoverages().get(0);
        assertEquals(1, storyCoverage1.getStory().getNumber());
        assertEquals(1.0, storyCoverage1.getCoverage());
        assertEquals("test1", storyCoverage1.getTestCases().get(0).getName());
        assertEquals("test2", storyCoverage1.getTestCases().get(1).getName());
        assertEquals("Story1StubTest", storyCoverage1.getTestCases().get(1).getTestClass().getName());

        StoryCoverage storyCoverage2 = result.getStoryCoverages().get(1);
        assertEquals(2, storyCoverage2.getStory().getNumber());
        assertEquals(0.0, storyCoverage2.getCoverage());
        assertEquals("test1", storyCoverage2.getTestCases().get(0).getName());
        assertEquals("test2", storyCoverage2.getTestCases().get(1).getName());
        assertEquals("Story2StubTest", storyCoverage2.getTestCases().get(1).getTestClass().getName());

        assertEquals(1, result.getIndependtantTestCases().size());
        assertEquals("test3", result.getIndependtantTestCases().get(0).getName());
    }
}
