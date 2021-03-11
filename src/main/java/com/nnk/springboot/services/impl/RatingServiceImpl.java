package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.IRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements IRatingService {

    @Autowired
    RatingRepository ratingRepository;

    public Rating getSpecificRatingById(Integer id) { return ratingRepository.findById(id).orElse(null); }

    public List<Rating> getAllRating() { return ratingRepository.findAll(); }

    public void createRating(Rating rating) { ratingRepository.save(rating); }

    public void updateRating(Integer id, Rating rating) {
        Rating ratingToUpdate = getSpecificRatingById(id);
        ratingToUpdate.setFitchRating(rating.getFitchRating());
        ratingToUpdate.setSandRating(rating.getSandRating());
        ratingToUpdate.setMoodysRating(rating.getMoodysRating());
        ratingToUpdate.setOrderNumber(rating.getOrderNumber());
        ratingRepository.save(ratingToUpdate);
    }

    public void deleteRating(Integer id) { ratingRepository.deleteById(id); }
}
