package com.forestservice.wildlifetracker.utility;

public enum AgeCategory {
    NEWBORN("newborn"),
    YOUNG("young"),
    ADULT("adult");

    private final String value;

    AgeCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

