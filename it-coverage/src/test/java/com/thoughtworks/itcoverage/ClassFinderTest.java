package com.thoughtworks.itcoverage;

import org.junit.Test;

import java.net.MalformedURLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class ClassFinderTest extends BaseClassFinderTest {
    @Test
    public void shouldFindAllClassesByPattern() throws MalformedURLException {
        List<Class> classes = new ClassFinder().findAll(getTestClassesDir(), ".*Story.*StubTest.*");
        assertEquals(2, classes.size());
        assertEquals("com.thoughtworks.itcoverage.stub.Story1StubTest", classes.get(0).getName());
        assertEquals("com.thoughtworks.itcoverage.stub.Story2StubTest", classes.get(1).getName());
    }

}
