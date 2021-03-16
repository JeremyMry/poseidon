package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RequestMapping("/user/list")
    public String home(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "user/list";
    }

    @GetMapping("/user/add")
    public String addUser(User user) {
        return "user/add";
    }

    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result) {
        if (result.hasErrors() || !userService.getUsernameAvailability(user.getUsername())) {
            FieldError error = new FieldError("user", "username", "The username is already taken");
            result.addError(error);
            return "user/add";
        }
        userService.createUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, User user) {
        model.addAttribute("user", userService.getSpecificUserById(id));
        return "user/update";
    }

    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }
        userService.updateUser(id, user);
        return "redirect:/user/list";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }
}
