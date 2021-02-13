package com.example.designpatterns.model.factory;

public interface Role {
    void setName(String name);
    String getName();
    String getRole();
    void checkAccess();
}
