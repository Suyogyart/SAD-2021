package com.example.designpatterns.model.factory;

import org.springframework.stereotype.Component;

@Component
public class RoleFactory {

    public Role createRole(String role) {
        switch(role.toLowerCase()) {
            case "admin":
                return new Admin();
            case "member":
                return new Member();
            default:
                throw new UnsupportedOperationException("Unsupported Role");
        }
    }
}
