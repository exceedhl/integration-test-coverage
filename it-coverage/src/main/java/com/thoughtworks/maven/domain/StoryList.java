package com.thoughtworks.maven.domain;

import java.util.ArrayList;

public class StoryList {
    ArrayList<Story> stories;

    public StoryList(ArrayList<Story> stories) {
        this.stories = stories;
    }

    public int size() {
        return stories.size();
    }

    public Story get(int index) {
        return stories.get(index);
    }

    public ArrayList<Story> getStories() {
        return stories;
    }
}
