package com.thoughtworks.itcoverage.stub;

import com.thoughtworks.itcoverage.annotation.Story;
import org.junit.Test;

public class Story2StubTest {
    @Test
    @Story(2)
    public void test1() {
    }

    @Test
    @Story(2)
    public void test2() {
    }

    @Test
    public void test3() {
    }
}