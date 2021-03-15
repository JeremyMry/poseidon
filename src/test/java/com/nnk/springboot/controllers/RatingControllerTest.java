package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.impl.CustomUserDetailsService;
import com.nnk.springboot.services.impl.RatingServiceImpl;
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

@WithMockUser
@WebMvcTest(RatingController.class)
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingServiceImpl ratingService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void ratingGetAllRatingControllerTest() throws Exception {
        List<Rating> ratingList = new ArrayList<>();

        when(ratingService.getAllRating()).thenReturn(ratingList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/rating/list"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("rating/list"))
                .andExpect(model().attributeExists("ratingList"));
    }

    @Test
    public void addRatingControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rating/add"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("rating/add"))
                .andExpect(model().attributeExists("rating"));
    }

    @Test
    public void validateRatingControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/rating/validate").with(csrf()).contentType(MediaType.ALL)
                .param("moodysRating", "a").param("sandRating", "a").param("fitchRating", "a").param("orderNumber", "15"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"));
    }

    @Test
    public void validateRatingControllerWithErrorTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/rating/validate").with(csrf()).contentType(MediaType.ALL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("rating/add"))
                .andExpect(model().attributeExists("rating"));
    }

    @Test
    public void showUpdateFormBidListControllerTest() throws Exception {
        Rating rating = new Rating();
        rating.setFitchRating("a");
        rating.setMoodysRating("a");
        rating.setSandRating("a");
        rating.setOrderNumber(1);

        when(ratingService.getSpecificRatingById(Mockito.any(Integer.class))).thenReturn(rating);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/rating/update/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("rating/update"))
                .andExpect(model().attributeExists("rating"));
    }

    @Test
    public void updateRatingControllerTest() throws Exception {
        Rating rating = new Rating();
        rating.setFitchRating("a");
        rating.setMoodysRating("a");
        rating.setSandRating("a");
        rating.setOrderNumber(1);

        when(ratingService.getSpecificRatingById(Mockito.any(Integer.class))).thenReturn(rating);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/rating/update/1").with(csrf()).contentType(MediaType.ALL)
                .param("moodysRating", "a").param("sandRating", "a").param("fitchRating", "a").param("orderNumber", "15"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"));
    }

    @Test
    public void updateRatingControllerWithErrorTest() throws Exception {
        Rating rating = new Rating();
        rating.setFitchRating("a");
        rating.setMoodysRating("a");
        rating.setSandRating("a");
        rating.setOrderNumber(1);

        when(ratingService.getSpecificRatingById(Mockito.any(Integer.class))).thenReturn(rating);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/rating/update/1").with(csrf()).contentType(MediaType.ALL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("rating/update"));
    }

    @Test
    public void deleteRatingControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/rating/delete/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/rating/list"));
    }
}
