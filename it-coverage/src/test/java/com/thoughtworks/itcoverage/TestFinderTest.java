package com.thoughtworks.itcoverage;

import com.thoughtworks.itcoverage.domain.TestCase;
import com.thoughtworks.itcoverage.domain.TestClass;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class TestFinderTest extends BaseClassFinderTest {

    @Test
    public void shouldBeAbleToFindAllTestsUnderSpecificPlace() throws MalformedURLException {
        File testClassesDir = getTestClassesDir();
        TestFinder testFinder = new TestFinder(testClassesDir, STORY_STUB_TEST_PATTERN);
        List<TestClass> tests = testFinder.findAll();
        assertEquals(2, tests.size());
        assertEquals("Story1StubTest", tests.get(0).getName());
        assertEquals((Object) 1, tests.get(0).getStoryNumber());
        assertEquals("Story2StubTest", tests.get(1).getName());
        assertEquals(null, tests.get(1).getStoryNumber());

        List<TestCase> testCases = tests.get(0).getTestCases();
        assertEquals(2, testCases.size());
        assertEquals(null, testCases.get(0).getStoryNumber());
        assertEquals((Object) 1, testCases.get(0).getTestClass().getStoryNumber());
        assertEquals("test1", testCases.get(0).getName());
        assertEquals(null, testCases.get(1).getStoryNumber());
        assertEquals("test2", testCases.get(1).getName());

        List<TestCase> testCases2 = tests.get(1).getTestCases();
        assertEquals(3, testCases2.size());
        assertEquals((Object) 2, testCases2.get(0).getStoryNumber());
        assertEquals((Object) 2, testCases2.get(1).getStoryNumber());
        assertNull(testCases2.get(2).getStoryNumber()
        );
    }

}
