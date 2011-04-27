package com.thoughtworks.itcoverage;

import com.thoughtworks.itcoverage.domain.TestCase;
import com.thoughtworks.itcoverage.domain.TestClass;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import static junit.framework.Assert.*;

public class TestFinderTest extends BaseTestFinderTest {

    @Test
    public void shouldBeAbleToFindAllTestsUnderSpecificPlace() throws MalformedURLException {
        File testClassesDir = getTestSourceDir();
        TestFinder testFinder = new TestFinder(testClassesDir, STORY_STUB_TEST_PATTERN);
        List<TestClass> tests = testFinder.findAllByJavaDoc();

        assertEquals(2, tests.size());
        assertContains(tests, "Story1StubTest");
        assertContains(tests, "Story2StubTest");

        TestClass test1 = getTestByName(tests, "Story1StubTest");
        assertEquals((Object) 1, test1.getStoryNumber());

        List<TestCase> testCases = test1.getTestCases();
        assertEquals(2, testCases.size());
        assertEquals(null, testCases.get(0).getStoryNumber());
        assertEquals((Object) 1, testCases.get(0).getTestClass().getStoryNumber());
        assertEquals("test1", testCases.get(0).getName());
        assertEquals(null, testCases.get(1).getStoryNumber());
        assertEquals("test2", testCases.get(1).getName());

        TestClass test2 = getTestByName(tests, "Story2StubTest");
        assertEquals(null, test2.getStoryNumber());
        List<TestCase> testCases2 = test2.getTestCases();

        assertEquals(3, testCases2.size());
        assertEquals((Object) 2, testCases2.get(0).getStoryNumber());
        assertEquals((Object) 2, testCases2.get(1).getStoryNumber());
        assertNull(testCases2.get(2).getStoryNumber());
    }

    private TestClass getTestByName(List<TestClass> tests, String testClassName) {
        for (TestClass c : tests) {
            if (c.getName().equals(testClassName)) {
                return c;
            }
        }
        return null;
    }

    private void assertContains(List<TestClass> tests, String testClassName) {
        boolean isContained = false;
        for (TestClass c : tests) {
            if (c.getName().equals(testClassName)) {
                isContained = true;
            }
        }
        assertTrue(isContained);
    }
}
