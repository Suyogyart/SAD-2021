package com.example.Lab2_Guide.security;
import com.example.Lab2_Guide.dao.UserJPADao;
import com.example.Lab2_Guide.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;




@Service
// What is @Service
// How is different from @Component or @Controller
// @Component - Autowired
// @Controller - @Component + some additional capabilities
// @Service - @Component + some additional com.example.Lab2_Guide.service capabilities
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserJPADao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userDao.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User 404");
        }

        return new UserDetailsImpl(user);
    }
    // Provides com.example.Lab2_Guide.service
    // To SecurityConfig
    // For getting some user detail information

}
