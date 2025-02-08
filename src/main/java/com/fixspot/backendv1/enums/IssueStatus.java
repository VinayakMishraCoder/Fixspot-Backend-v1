package com.fixspot.backendv1.enums;


import java.util.Arrays;
import java.util.Objects;

public enum IssueStatus {

    UNRESOLVED, ONGOING, RESOLVED;

    public static Boolean isValid(String status) {
        return Arrays.stream(values()).anyMatch(x -> Objects.equals(status, x.name()));
    }
}
