package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.IRatingService;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RatingServiceImpl implements IRatingService {

    private RatingRepository ratingRepository;

    private Logger logger;

    public RatingServiceImpl(RatingRepository ratingRepository, Logger logger) {
        this.ratingRepository = ratingRepository;
        this.logger = logger;
    }

    @Override
    public Rating getSpecificRatingById(Integer id) {
        logger.info("Rating " + id + " find");
        return ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id: " + id));
    }

    @Override
    public List<Rating> getAllRating() {
        logger.info("Rating List find");
        return ratingRepository.findAll();
    }

    @Override
    @Transactional
    public void createRating(Rating rating) {
        logger.info("Rating created");
        ratingRepository.save(rating);
    }

    @Override
    @Transactional
    public void updateRating(Integer id, Rating rating) {
        Rating ratingToUpdate = getSpecificRatingById(id);
        ratingToUpdate.setFitchRating(rating.getFitchRating());
        ratingToUpdate.setSandRating(rating.getSandRating());
        ratingToUpdate.setMoodysRating(rating.getMoodysRating());
        ratingToUpdate.setOrderNumber(rating.getOrderNumber());
        logger.info("Rating " + id + " updated");
        ratingRepository.save(ratingToUpdate);
    }

    @Override
    @Transactional
    public void deleteRating(Integer id) {
        logger.info("Rating " + id + " deleted");
        ratingRepository.deleteById(id);
    }
}
