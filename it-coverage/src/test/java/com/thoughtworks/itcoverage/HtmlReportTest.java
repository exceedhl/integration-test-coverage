package com.thoughtworks.itcoverage;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class HtmlReportTest extends BaseTestFinderTest {
    @Test
    public void shouldGenerateHtmlReport() throws IOException {
        StoryFinder storyFinder = new StoryFinder("http://localhost:5555/storiesWithAC.xml", "whatever", "whatever",
                new ACParser());
        TestCoverageCalculator calculator = new TestCoverageCalculator(storyFinder,
                new TestFinder(getTestSourceDir(), STORY_STUB_TEST_PATTERN));
        File outputDir = getTestClassesDir();
        HtmlReporter htmlReporter = new HtmlReporter(calculator, outputDir);
        htmlReporter.generateReport();
        String reportFilePath = outputDir + File.separator + "it-coverage-report.html";
        File file = new File(reportFilePath);
        assertTrue(file.exists());
        String fileContent = readFile(file);
        assertTrue(fileContent.contains("Story1StubTest.test1"));
        assertTrue(fileContent.contains("Story1StubTest.test2"));
        assertTrue(fileContent.contains("Story2StubTest.test1"));
        assertTrue(fileContent.contains("Story2StubTest.test2"));
        assertTrue(fileContent.contains("Story2StubTest.test3"));
        assertTrue(fileContent.contains("当用户发布动态信息时，用户的好友收到该用户的动态信息。"));
        assertTrue(fileContent.contains("当用户发布动态信息后，用户的好友由离线变为在线时收到该用户的动态信息。"));
        file.delete();
    }

    private static String readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        String nextLine = "";
        StringBuffer sb = new StringBuffer();
        while ((nextLine = br.readLine()) != null) {
            sb.append(nextLine);
            sb.append(File.separator);
        }
        return sb.toString();
    }
}
