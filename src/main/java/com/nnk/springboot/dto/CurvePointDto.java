package com.nnk.springboot.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CurvePointDto {

    @NotNull(message = "{NotNull.Field.Limitation}")
    @Min(value = 1, message= "{MinValue.Field.Integer}")
    private Integer curveId;

    @NotNull(message = "{NotNull.Field.Limitation}")
    @Min(value = 0, message = "{Size.Field.Double}")
    private Double term;

    @NotNull(message = "{NotNull.Field.Limitation}")
    @Min(value = 0, message = "{Size.Field.Double}")
    private Double value;

    public CurvePointDto(@NotNull(message = "{NotNull.Field.Limitation}") @Min(value = 1, message = "{MinValue.Field.Integer}") Integer curveId, @NotNull(message = "{NotNull.Field.Limitation}") @Min(value = 0, message = "{Size.Field.Double}") Double term, @NotNull(message = "{NotNull.Field.Limitation}") @Min(value = 0, message = "{Size.Field.Double}") Double value) {
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
