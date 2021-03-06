package com.thoughtworks.itcoverage;

import com.thoughtworks.itcoverage.domain.Story;
import com.thoughtworks.itcoverage.domain.StoryList;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class StoryFinderTest {
    @Test
    public void shouldBeAbleToFindAllStoryFromMingle() {
        StoryFinder storyFinder = new StoryFinder("http://localhost:5555/storiesWithAC.xml", "whatever", "whatever",
                new ACParser());
        StoryList stories = storyFinder.findAll();
        assertEquals(2, stories.size());
        Story story1 = stories.get(0);
        assertEquals(1, story1.getNumber());
        assertEquals("Login to the e-mail application", story1.getName());
        assertEquals(2, story1.getAcceptanceCriteria().size());
    }

    @Test
    public void shouldHandleStoriesWithoutAC() {
        StoryFinder storyFinder = new StoryFinder("http://localhost:5555/storiesWithoutAC.xml", "whatever", "whatever",
                new ACParser());
        StoryList stories = storyFinder.findAll();
        assertEquals(1, stories.size());
        assertEquals(0, stories.get(0).getAcceptanceCriteria().size());
    }
}
