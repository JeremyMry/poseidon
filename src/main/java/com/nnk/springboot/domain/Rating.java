package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Size(min = 1, max = 125, message= "{Size.Field.String}")
    private String moodysRating;

    @Size(min = 1, max = 125, message= "{Size.Field.String}")
    private String sandRating;

    @Size(min = 1, max = 125, message= "{Size.Field.String}")
    private String fitchRating;

    @Min(value = 1, message= "{MinValue.Field.Integer}")
    private Integer orderNumber;

    public Rating() {
    }

    public Rating(@Size(min = 1, max = 125, message = "{Size.Field.String}") String moodysRating, @Size(min = 1, max = 125, message = "{Size.Field.String}") String sandRating, @Size(min = 1, max = 125, message = "{Size.Field.String}") String fitchRating, @Min(value = 1, message = "{MinValue.Field.Integer}") Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandRating = sandRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandRating() {
        return sandRating;
    }

    public void setSandRating(String sandRating) {
        this.sandRating = sandRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
