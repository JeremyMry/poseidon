package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.impl.CustomUserDetailsService;
import com.nnk.springboot.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collections;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomUserDetailsServiceTest {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Test
    public void loadUserByUsernameTest() {
        User user = new User("john", "Atemipro123;", "john doe", "ADMIN");
        userService.createUser(user);

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));

        Assertions.assertEquals(customUserDetailsService.loadUserByUsername("john"), userDetails);
    }
}
