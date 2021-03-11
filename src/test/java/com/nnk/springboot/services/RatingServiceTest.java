package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.impl.RatingServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class RatingServiceTest {

    @Autowired
    RatingServiceImpl ratingService;

    @Autowired
    RatingRepository ratingRepository;

    @Test
    public void getSpecificBidListTest() {
        Rating rating = new Rating();
        rating.setSandRating("aaa");
        rating.setMoodysRating("aa");
        rating.setFitchRating("bbb");
        rating.setOrderNumber(15);

        ratingRepository.save(rating);

        Assertions.assertNotNull(ratingService.getSpecificRatingById(1));
        Assertions.assertEquals(ratingService.getSpecificRatingById(1).getFitchRating(), rating.getFitchRating());
        Assertions.assertEquals(ratingService.getSpecificRatingById(1).getSandRating(), rating.getSandRating());
        Assertions.assertEquals(ratingService.getSpecificRatingById(1).getMoodysRating(), rating.getMoodysRating());
        Assertions.assertEquals(ratingService.getSpecificRatingById(1).getOrderNumber(), rating.getOrderNumber());
    }

    @Test
    public void getSpecificBidListThatDoesntExistTest() {
        Assertions.assertNull(ratingService.getSpecificRatingById(1));
    }

    @Test
    public void getAllBidListTest() {
        Rating rating = new Rating();
        rating.setSandRating("aaa");
        rating.setMoodysRating("aa");
        rating.setFitchRating("bbb");
        rating.setOrderNumber(15);

        Rating rating1 = new Rating();
        rating1.setSandRating("ccc");
        rating1.setMoodysRating("cc");
        rating1.setFitchRating("ddd");
        rating1.setOrderNumber(18);

        ratingRepository.save(rating);
        ratingRepository.save(rating1);

        Assertions.assertEquals(ratingService.getAllRating().size(), 2);
    }

    @Test
    public void getAllBidListWhenThereIsNone() {
        Assertions.assertEquals(ratingService.getAllRating().size(), 0);
        Assertions.assertNotNull(ratingService.getAllRating());
    }

    @Test
    public void createBidListTest() {
        Rating rating = new Rating();
        rating.setSandRating("aaa");
        rating.setMoodysRating("aa");
        rating.setFitchRating("bbb");
        rating.setOrderNumber(15);

        ratingService.createRating(rating);

        Assertions.assertEquals(ratingRepository.findById(1).get().getSandRating(), rating.getSandRating());
        Assertions.assertEquals(ratingRepository.findById(1).get().getMoodysRating(), rating.getMoodysRating());
        Assertions.assertEquals(ratingRepository.findById(1).get().getFitchRating(), rating.getFitchRating());
        Assertions.assertEquals(ratingRepository.findById(1).get().getOrderNumber(), rating.getOrderNumber());
    }

    @Test
    public void updateBidListTest() {
        Rating rating = new Rating();
        rating.setSandRating("aaa");
        rating.setMoodysRating("aa");
        rating.setFitchRating("bbb");
        rating.setOrderNumber(15);
        ratingRepository.save(rating);

        Rating rating1 = new Rating();
        rating1.setSandRating("ccc");
        rating1.setMoodysRating("cc");
        rating1.setFitchRating("ddd");
        rating1.setOrderNumber(18);

        ratingService.updateRating(1, rating1);

        Assertions.assertEquals(ratingRepository.findById(1).get().getSandRating(), "ccc");
        Assertions.assertEquals(ratingRepository.findById(1).get().getFitchRating(), "ddd");
        Assertions.assertEquals(ratingRepository.findById(1).get().getMoodysRating(), "cc");
        Assertions.assertEquals(java.util.Optional.ofNullable(ratingRepository.findById(1).get().getOrderNumber()), java.util.Optional.ofNullable(18));
        Assertions.assertEquals(ratingRepository.findAll().size(), 1);
    }

    @Test
    public void deleteBidListTest() {
        Rating rating = new Rating();
        rating.setSandRating("aaa");
        rating.setMoodysRating("aa");
        rating.setFitchRating("bbb");
        rating.setOrderNumber(15);
        ratingRepository.save(rating);

        ratingService.deleteRating(1);

        Assertions.assertFalse(ratingRepository.findById(1).isPresent());
    }

}
