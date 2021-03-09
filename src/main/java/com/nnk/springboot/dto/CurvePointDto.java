package com.nnk.springboot.dto;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class CurvePointDto {

    @NotNull(message = "{NotNull.CurvePoint.CurveId}")
    private Integer curveId;

    private Double term;

    private Double value;

    public CurvePointDto(@NotNull Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
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
}
