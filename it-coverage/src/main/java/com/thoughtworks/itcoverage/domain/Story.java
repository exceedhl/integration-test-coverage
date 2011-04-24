package com.thoughtworks.itcoverage.domain;

import java.util.ArrayList;
import java.util.List;

public class Story {
    int number;
    String name;
    String description;
    Properties properties;

    List<String> acceptanceCriteria;

    public Story(int number, String name, String description) {
        this.number = number;
        this.name = name;
        this.description = description;

        this.acceptanceCriteria = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public void setAcceptanceCriteria(List<String> acceptanceCriteria) {
        this.acceptanceCriteria = acceptanceCriteria;
    }

    public List<String> getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public String getDescription() {
        return description;
    }

    public int getNumber() {
        return number;
    }

    public void addAC(String ac) {
        acceptanceCriteria.add(ac);
    }

    public boolean hasAC() {
        return !acceptanceCriteria.isEmpty();
    }
}
