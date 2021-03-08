package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;

import java.util.List;

public interface IBidListService {

    public BidList getSpecificBidListById(Integer id);

    public List<BidList> getAllBidList();

    public void updateBidList(Integer id, BidListDto BidListDto);

    public void createBidList(BidListDto bid);

    public void deleteBidList(Integer id);
}
