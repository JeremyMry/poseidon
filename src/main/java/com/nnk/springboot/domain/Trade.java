package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

@Entity
public class Trade {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer tradeId;

    @NotNull
    @NotEmpty
    @Size(max =30)
    private String account;

    @NotNull
    @NotEmpty
    @Size(max =30)
    private String type;

    @NotNull
    @Min(value = 0)
    private Double buyQuantity;

    private Double sellQuantity;

    private Double buyPrice;

    private Double sellPrice;

    private Timestamp tradeDate;

    @Size(max = 125)
    private String security;

    @Size(max = 125)
    private String status;

    @Size(max = 125)
    private String trader;

    @Size(max = 125)
    private String benchmark;

    @Size(max = 125)
    private String book;

    @Size(max = 125)
    private String creationName;

    private Timestamp creationDate;

    @Size(max = 125)
    private String revisionName;

    private Timestamp revisionDate;

    @Size(max = 125)
    private String dealName;

    @Size(max = 125)
    private String dealType;

    @Size(max = 125)
    private String sourceListId;

    @Size(max = 125)
    private String side;

    public Trade() {
    }

    public Trade(@NotNull @Size(max = 30) String account, @NotNull @Size(max = 30) String type, @NotNull @Min(value = 0) Double buyQuantity, Double sellQuantity, Double buyPrice, Double sellPrice, Timestamp tradeDate, @Size(max = 125) String security, @Size(max = 125) String status, @Size(max = 125) String trader, @Size(max = 125) String benchmark, @Size(max = 125) String book, @Size(max = 125) String creationName, Timestamp creationDate, @Size(max = 125) String revisionName, Timestamp revisionDate, @Size(max = 125) String dealName, @Size(max = 125) String dealType, @Size(max = 125) String sourceListId, @Size(max = 125) String side) {
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
        this.sellQuantity = sellQuantity;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.tradeDate = new Timestamp(tradeDate.getTime());
        this.security = security;
        this.status = status;
        this.trader = trader;
        this.benchmark = benchmark;
        this.book = book;
        this.creationName = creationName;
        this.creationDate = new Timestamp(creationDate.getTime());
        this.revisionName = revisionName;
        this.revisionDate = new Timestamp(revisionDate.getTime());
        this.dealName = dealName;
        this.dealType = dealType;
        this.sourceListId = sourceListId;
        this.side = side;
    }

    public Integer getTradeId() {
        return tradeId;
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

    public Double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(Double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getTradeDate() {
        return new Timestamp(tradeDate.getTime());
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = new Timestamp(tradeDate.getTime());
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public Timestamp getCreationDate() {
        return new Timestamp(creationDate.getTime());
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = new Timestamp(creationDate.getTime());
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public Timestamp getRevisionDate() {
        return new Timestamp(revisionDate.getTime());
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = new Timestamp(revisionDate.getTime());
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
