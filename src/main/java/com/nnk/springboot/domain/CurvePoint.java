package com.nnk.springboot.domain;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
public class CurvePoint {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Min(value = 1)
    private Integer curveId;

    private Timestamp asOfDate;

    @NotNull
    @Min(value = 0)
    private Double term;

    @NotNull
    @Min(value = 0)
    private Double value;

    private Timestamp creationDate;

    public CurvePoint() {
    }

    public CurvePoint(@NotNull @Min(value = 1) Integer curveId, Timestamp asOfDate, @NotNull @Min(value = 0) Double term, @NotNull @Min(value = 0) Double value, Timestamp creationDate) {
        this.curveId = curveId;
        this.asOfDate = new Timestamp(asOfDate.getTime());
        this.term = term;
        this.value = value;
        this.creationDate = new Timestamp(creationDate.getTime());
    }

    public Integer getId() {
        return id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate() {
        return new Timestamp(asOfDate.getTime());
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = new Timestamp(asOfDate.getTime());
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Timestamp getCreationDate() {
        return new Timestamp(creationDate.getTime());
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = new Timestamp(creationDate.getTime());
    }

    @Override
    public String toString() {
        return "CurvePoint{" +
                "id=" + id +
                ", curveId=" + curveId +
                ", asOfDate=" + asOfDate +
                ", term=" + term +
                ", value=" + value +
                ", creationDate=" + creationDate +
                '}';
    }
}
