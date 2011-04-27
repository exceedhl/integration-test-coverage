package com.thoughtworks.itcoverage;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String resourceUrl = System.getProperty("resourceUrl");
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        System.out.println(System.getProperty("testClassesDir"));
        System.out.println(System.getProperty("resourceUrl"));
        File testClassesDir = new File(System.getProperty("testClassesDir"));
        String testPattern = System.getProperty("testPattern");
        String reportOutputDir = System.getProperty("reportOutputDir");

        StoryFinder storyFinder = new StoryFinder(resourceUrl, username, password, new ACParser());
        TestCoverageCalculator calculator = new TestCoverageCalculator(storyFinder,
                new TestFinder(testClassesDir, testPattern));
        HtmlReporter htmlReporter = new HtmlReporter(calculator, new File(reportOutputDir));
        htmlReporter.generateReport();
    }
}
