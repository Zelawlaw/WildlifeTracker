package com.forestservice.wildlifetracker.utility;

public enum HealthStatus {
    HEALTHY("healthy"),
    ILL("ill"),
    OKAY("okay");

    private final String value;

    HealthStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
