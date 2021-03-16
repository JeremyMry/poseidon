package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;

@Entity
public class BidList {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer bidListId;

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
    private Double bidQuantity;

    private Double askQuantity;

    private Double bid;

    private Double ask;

    @Size(max=125)
    private String benchmark;

    private Timestamp bidListDate;

    @Size(max=125)
    private String commentary;

    @Size(max=125)
    private String security;

    @Size(max=10)
    private String status;

    @Size(max=125)
    private String trader;

    @Size(max=125)
    private String book;

    @Size(max=125)
    private String creationName;

    private Timestamp creationDate;

    @Size(max=125)
    private String revisionName;

    private Timestamp revisionDate;

    @Size(max=125)
    private String dealName;

    @Size(max=125)
    private String dealType;

    @Size(max=125)
    private String sourceListId;

    @Size(max=125)
    private String side;

    public BidList() {}

    public BidList(@NotNull @NotEmpty @Size(max = 30) String account, @NotNull @NotEmpty @Size(max = 30) String type, @NotNull @Min(value = 0) Double bidQuantity, Double askQuantity, Double bid, Double ask, @Size(max = 125) String benchmark, Timestamp bidListDate, @Size(max = 125) String commentary, @Size(max = 125) String security, @Size(max = 10) String status, @Size(max = 125) String trader, @Size(max = 125) String book, @Size(max = 125) String creationName, Timestamp creationDate, @Size(max = 125) String revisionName, Timestamp revisionDate, @Size(max = 125) String dealName, @Size(max = 125) String dealType, @Size(max = 125) String sourceListId, @Size(max = 125) String side) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
        this.askQuantity = askQuantity;
        this.bid = bid;
        this.ask = ask;
        this.benchmark = benchmark;
        this.bidListDate = new Timestamp(bidListDate.getTime());
        this.commentary = commentary;
        this.security = security;
        this.status = status;
        this.trader = trader;
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

    public Integer getBidListId() {
        return bidListId;
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

    public Double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(Double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getBidListDate() {
        if(this.bidListDate == null){
            return null;
        }
        return new Timestamp(this.bidListDate.getTime());
    }

    public void setBidListDate(Timestamp bidListDate) {
        this.bidListDate = new Timestamp(this.bidListDate.getTime());
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
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
