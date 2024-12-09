package com.arthurprojects.exchange.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StudentType {
    RESIDENTIAL_SELF_SPONSORED("RESIDENTIAL_SELF_SPONSORED"),
    RESIDENTIAL_FINANCIAL_AID("RESIDENTIAL_FINANCIAL_AID"),
    DAY_SCHOLAR_SELF_SPONSORED("DAY_SCHOLAR_SELF_SPONSORED"),
    DAY_SCHOLAR_FINANCIAL_AID("DAY_SCHOLAR_FINANCIAL_AID");

    private final String value;

    StudentType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static StudentType fromValue(String value) {
        for (StudentType type : StudentType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown StudentType: " + value);
    }
}
