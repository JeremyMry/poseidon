package com.nnk.springboot.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class BidListDto {

    @NotNull(message = "{NotEmpty.BidList.Account}")
    @NotEmpty(message = "{NotEmpty.BidList.Account}")
    private String account;

    @NotNull(message = "{NotEmpty.BidList.Type}")
    @NotEmpty(message = "{NotEmpty.BidList.Type}")
    private String type;

    @NotNull(message = "{NotNull.BidList.Quantity}")
    @Min(value = 0, message = "{MinValue.BidList.Quantity}")
    private Double bidQuantity;

    public BidListDto(String account, String type, Double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }
}
