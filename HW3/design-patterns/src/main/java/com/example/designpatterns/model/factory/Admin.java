package com.example.designpatterns.model.factory;

public class Admin implements Role {

    private String name;

    public Admin() {
        super();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    @Override
    public void checkAccess() {
        System.out.println("Granted access level: Admin");
    }
}
