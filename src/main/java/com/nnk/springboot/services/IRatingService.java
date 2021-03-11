package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;

import java.util.List;

public interface IRatingService {

    public Rating getSpecificRatingById(Integer id);

    public List<Rating> getAllRating();

    public void createRating(Rating rating);

    public void updateRating(Integer id, Rating rating);

    public void deleteRating(Integer id);

}
