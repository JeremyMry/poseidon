package com.nnk.springboot.dto;

import javax.validation.constraints.*;

public class TradeDto {

    @NotNull(message = "{NotNull.Field.Limitation}")
    @NotEmpty(message = "{Empty.Field.String}")
    @Size(max =30, message = "{Size.Field.Limitation}")
    private String account;

    @NotNull(message = "{NotNull.Field.Limitation}")
    @NotEmpty(message = "{Empty.Field.String}")
    @Size(max =30, message = "{Size.Field.Limitation}")
    private String type;

    @NotNull(message = "{NotNull.Field.Limitation}")
    @Min(value = 0, message = "{Size.Field.Double}")
    private Double buyQuantity;

    public TradeDto(@NotNull(message = "{NotNull.Field.Limitation}") @NotEmpty(message = "{Empty.Field.String}") @Size(max = 30, message = "{Size.Field.Limitation}") String account, @NotNull(message = "{NotNull.Field.Limitation}") @NotEmpty(message = "{Empty.Field.String}") @Size(max = 30, message = "{Size.Field.Limitation}") String type, @NotNull(message = "{NotNull.Field.Limitation}") @Min(value = 0, message = "{Size.Field.Double}") Double buyQuantity) {
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
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

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }
}
