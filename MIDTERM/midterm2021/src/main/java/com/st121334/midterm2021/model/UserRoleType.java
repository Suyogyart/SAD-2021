package com.st121334.midterm2021.model;

public enum UserRoleType {
    ROLE_ADMIN(Values.ROLE_ADMIN),
    ROLE_USER(Values.ROLE_USER);

    private final String val;

    UserRoleType(String val) {
        if (!this.name().equals(val)) {
            throw new IllegalArgumentException("Incorrect use of enums");
        } else {
            this.val = val;
        }
    }

    public static class Values {
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_USER = "ROLE_USER";
    }
}