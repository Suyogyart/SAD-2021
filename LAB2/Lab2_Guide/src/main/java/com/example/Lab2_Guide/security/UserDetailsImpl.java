package com.example.Lab2_Guide.security;

import com.example.Lab2_Guide.model.Role;
import com.example.Lab2_Guide.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {
    // Who will use this?
    // MyUserDetailsService
    // This gives all the user details

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // Important
        // Once we get user, what authority we give to the user
        // Return collection of authorities
        // Hashset gives more performance
        // We have to give authority based on roles
        // One user has many roles
        // Auth will have many auth so we must have auth.add
        Set<GrantedAuthority> auth = new HashSet<>();

        for (Role role: user.getRoles()) {
            auth.add(new SimpleGrantedAuthority(role.getName()));
        }

        return auth;
    }

    public int getID() {
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
