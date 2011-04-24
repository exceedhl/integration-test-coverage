package com.thoughtworks.itcoverage;

import com.thoughtworks.itcoverage.domain.Story;
import com.thoughtworks.itcoverage.domain.StoryList;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ACParser {
    private static final String AC_SECTION_START = "h1. 验收策略";

    public void parseAC(StoryList stories) {
        for (Story story : stories.getStories()) {
            story.setAcceptanceCriteria(new ArrayList<String>());
            parseAC(story);
        }
    }

    void parseAC(Story story) {
        String description = story.getDescription();
        int acStartIndex = description.indexOf(AC_SECTION_START);
        if (acStartIndex < 0) {
            return;
        }
        String acText = description.substring(acStartIndex);
        Pattern pattern = Pattern.compile("#\\s*(.*)");
        Matcher matcher = pattern.matcher(acText);
        while (matcher.find()) {
            String group = matcher.group(1);
            story.addAC(group);
        }
    }
}
