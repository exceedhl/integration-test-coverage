package com.thoughtworks.itcoverage;

import com.thoughtworks.itcoverage.domain.CoverageResult;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.*;
import java.util.Properties;

public class HtmlReporter {
    private static final String HTML_REPORT_VM_FILE = "HtmlReport.vm";
    private static final String IT_COVERAGE_REPORT_RESULT_HTML_FILE = "it-coverage-report.html";
    private TestCoverageCalculator testCoverageCalculator;
    private File outputDir;

    public HtmlReporter(TestCoverageCalculator testCoverageCalculator, File outputDir) {
        this.testCoverageCalculator = testCoverageCalculator;
        this.outputDir = outputDir;
    }

    public void generateReport() throws IOException {
        initVelocity();
        VelocityContext context = new VelocityContext();
        CoverageResult result = testCoverageCalculator.calculate();
        context.put("result", result);

        FileUtils.forceMkdir(outputDir);

        Writer w = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(new File(outputDir + File.separator + IT_COVERAGE_REPORT_RESULT_HTML_FILE)),
                "UTF-8"));
        Velocity.getTemplate(HTML_REPORT_VM_FILE).merge(context, w);
        w.close();

    }

    private void initVelocity() {
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(p);
    }
}
