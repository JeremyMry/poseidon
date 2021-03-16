package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 125, message= "{Size.Field.String}")
    @NotEmpty(message = "{Empty.Field.String}")
    private String moodysRating;

    @Size(max = 125, message= "{Size.Field.String}")
    @NotEmpty(message = "{Empty.Field.String}")
    private String sandRating;

    @Size(max = 125, message= "{Size.Field.String}")
    @NotEmpty(message = "{Empty.Field.String}")
    private String fitchRating;

    @Min(value = 1, message= "{MinValue.Field.Integer}")
    @NotNull(message = "{NotNull.Field.Limitation}")
    private Integer orderNumber;

    public Rating() {
    }

    public Rating(@Size(max = 125, message = "{Size.Field.String}") @NotEmpty(message = "{Empty.Field.String}") String moodysRating, @Size(max = 125, message = "{Size.Field.String}") @NotEmpty(message = "{Empty.Field.String}") String sandRating, @Size(max = 125, message = "{Size.Field.String}") @NotEmpty(message = "{Empty.Field.String}") String fitchRating, @Min(value = 1, message = "{MinValue.Field.Integer}") @NotNull(message = "{NotNull.Field.Limitation}") Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandRating = sandRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    public Integer getId() {
        return id;
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
