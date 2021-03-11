package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.IRatingService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements IRatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    Logger logger;

    public Rating getSpecificRatingById(Integer id) {
        logger.info("Rating " + id + " find");
        return ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id: " + id));
    }

    public List<Rating> getAllRating() {
        logger.info("Rating List find");
        return ratingRepository.findAll();
    }

    public void createRating(Rating rating) {
        logger.info("Rating created");
        ratingRepository.save(rating);
    }

    public void updateRating(Integer id, Rating rating) {
        Rating ratingToUpdate = getSpecificRatingById(id);
        ratingToUpdate.setFitchRating(rating.getFitchRating());
        ratingToUpdate.setSandRating(rating.getSandRating());
        ratingToUpdate.setMoodysRating(rating.getMoodysRating());
        ratingToUpdate.setOrderNumber(rating.getOrderNumber());
        logger.info("Rating " + id + " updated");
        ratingRepository.save(ratingToUpdate);
    }

    public void deleteRating(Integer id) {
        logger.info("Rating " + id + " deleted");
        ratingRepository.deleteById(id);
    }
}
