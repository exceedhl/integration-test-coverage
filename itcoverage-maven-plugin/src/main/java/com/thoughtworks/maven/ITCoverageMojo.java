package com.thoughtworks.maven;

import com.thoughtworks.itcoverage.*;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;
import java.io.IOException;

/**
 * Goal which analyze story acceptance criteria and testcases to calculate integration test coverage
 *
 * @goal coverage
 * @requiresDependencyResolution test
 */
public class ITCoverageMojo extends AbstractMojo {
    /**
     * Mingle story cards resource url
     *
     * @parameter expression="${itcoverage.resourceUrl}"
     * @required
     */
    private String resourceUrl;

    /**
     * Mingle username
     *
     * @parameter expression="${itcoverage.username}" default-value=""
     * @required
     */
    private String username;

    /**
     * Mingle password
     *
     * @parameter expression="${itcoverage.password}" default-value=""
     * @required
     */
    private String password;

    /**
     * Integration test classes directory
     *
     * @parameter expression="${itcoverage.testClassesDir}" default-value="src/test/java"
     */
    private File testClassesDir;

    /**
     * Pattern for testclass searching
     *
     * @parameter expression="${itcoverage.testPattern}" default-value=".*"
     */
    private String testPattern;

    /**
     * Report output dir
     *
     * @parameter expression="${itcoverage.reportOutputDir}" default-value="target/itcoverage"
     */
    private File reportOutputDir;

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            StoryFinder storyFinder = new StoryFinder(resourceUrl, username, password, new ACParser());
            TestCoverageCalculator calculator = new TestCoverageCalculator(storyFinder,
                    new TestFinder(testClassesDir, testPattern));
            HtmlReporter htmlReporter = new HtmlReporter(calculator, reportOutputDir);
            htmlReporter.generateReport();
        } catch (IOException e) {
            throw new MojoExecutionException("Error happened while generating reports", e);
        }
    }
}
