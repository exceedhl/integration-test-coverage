package com.thoughtworks.itcoverage.domain;

import java.util.ArrayList;
import java.util.List;

public class CoverageResult {
    private List<StoryCoverage> storyCoverages;
    private List<TestCase> independtantTestCases;

    public CoverageResult() {
        storyCoverages = new ArrayList<StoryCoverage>();
        independtantTestCases = new ArrayList<TestCase>();
    }

    public List<StoryCoverage> getStoryCoverages() {
        return storyCoverages;
    }

    public List<TestCase> getIndependtantTestCases() {
        return independtantTestCases;
    }

    public void addIndependantTestCase(TestCase testCase) {
        this.independtantTestCases.add(testCase);
    }

    public void addStoryCovereage(StoryCoverage storyCoverage) {
        this.storyCoverages.add(storyCoverage);
    }

    public Double getAverageCoverage() {
        Double total = 0.0;
        for (StoryCoverage c : storyCoverages) {
            total += c.getCoverage();
        }
        return total / storyCoverages.size();
    }
}
