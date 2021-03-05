package com.example.Lab3.model;

public enum LeaveType {
    SICK(Values.SICK), ANNUAL(Values.ANNUAL);

    LeaveType(String val) {
        if (!this.name().equals(val))
            throw new IllegalArgumentException("Incorrect use of ELanguage");
    }

    public static class Values {
        public static final String SICK = "SICK";
        public static final String ANNUAL = "ANNUAL";
    }

}

