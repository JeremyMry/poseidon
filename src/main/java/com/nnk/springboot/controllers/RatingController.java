package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.impl.RatingServiceImpl;
import com.nnk.springboot.services.impl.UserInfoImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class RatingController {

    private RatingServiceImpl ratingService;

    private UserInfoImpl userInfoImpl;

    public RatingController(RatingServiceImpl ratingService, UserInfoImpl userInfoImpl) {
        this.ratingService = ratingService;
        this.userInfoImpl = userInfoImpl;
    }

    @RequestMapping("/rating/list")
    public String home(Model model, Principal user) {
        model.addAttribute("user", userInfoImpl.getUserInfo(user));
        model.addAttribute("ratingList", ratingService.getAllRating());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result) {
        if(result.hasErrors()) {
            return "rating/add";
        }
        ratingService.createRating(rating);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model, Rating rating) {
        model.addAttribute("ratingUpdate", ratingService.getSpecificRatingById(id));
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("ratingUpdate", ratingService.getSpecificRatingById(id));
            return "rating/update";
        }
        ratingService.updateRating(id, rating);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id) {
        ratingService.deleteRating(id);
        return "redirect:/rating/list";
    }
}
