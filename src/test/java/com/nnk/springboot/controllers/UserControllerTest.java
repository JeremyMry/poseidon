package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.impl.CustomUserDetailsService;
import com.nnk.springboot.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WithMockUser(authorities = "ADMIN")
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void bidListGetAllUsersControllerTest() throws Exception {
        List<User> userList = new ArrayList<>();

        when(userService.getAllUser()).thenReturn(userList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/list"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"));
    }

    @Test
    public void addUserControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/add"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/add"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void validateUserControllerTest() throws Exception {

        when(userService.getUsernameAvailability(Mockito.any(String.class))).thenReturn(true);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/validate").with(csrf()).contentType(MediaType.ALL)
                .param("username", "aaa").param("password", "Greatpassword1;").param("fullname", "15.0").param("role", "ADMIN"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"));
    }

    @Test
    public void validateUserUnavailableUsernameControllerTest() throws Exception {
        userService.createUser(new User("aaa", "Greatpassword1;", "15.0", "ADMIN"));
        
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/validate").with(csrf()).contentType(MediaType.ALL)
                .param("username", "aaa").param("password", "Greatpassword1;").param("fullname", "15.0").param("role", "ADMIN"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/add"));
    }

    @Test
    public void validateUserControllerWithErrorTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/validate").with(csrf()).contentType(MediaType.ALL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/add"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void showUpdateFormUserControllerTest() throws Exception {
        User user = new User();
        user.setUsername("a");
        user.setPassword("Greatpassword1;");
        user.setFullname("a");
        user.setRole("ADMIN");

        when(userService.getSpecificUserById(Mockito.any(Integer.class))).thenReturn(user);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/update/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/update"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void updateUserControllerTest() throws Exception {
        User user = new User();
        user.setUsername("a");
        user.setPassword("Greatpassword1;");
        user.setFullname("a");
        user.setRole("ADMIN");

        when(userService.getSpecificUserById(Mockito.any(Integer.class))).thenReturn(user);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/update/1").with(csrf()).contentType(MediaType.ALL)
                .param("username", "a").param("password", "Greatpassword1;").param("fullname", "15.0").param("role", "ADMIN"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"));
    }

    @Test
    public void updateUserControllerWithErrorTest() throws Exception {
        User user = new User();
        user.setUsername("a");
        user.setPassword("Greatpassword1;");
        user.setFullname("a");
        user.setRole("ADMIN");

        when(userService.getSpecificUserById(Mockito.any(Integer.class))).thenReturn(user);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/update/1").with(csrf()).contentType(MediaType.ALL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/update"));
    }

    @Test
    public void deleteUserControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/delete/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/list"));
    }
}
