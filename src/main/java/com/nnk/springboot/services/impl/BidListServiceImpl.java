package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.IBidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class BidListServiceImpl implements IBidListService {

    @Autowired
    BidListRepository bidListRepository;

    @Override
    public BidList getSpecificBidListById(Integer id) { return bidListRepository.findById(id).orElse(null); }

    @Override
    public List<BidList> getAllBidList() {
        return bidListRepository.findAll();
    }

    @Override
    @Transactional
    public void createBidList(BidListDto bid) {
        BidList bidList = new BidList();
        bidList.setAccount(bid.getAccount());
        bidList.setType(bid.getType());
        bidList.setBidQuantity(bid.getBidQuantity());
        bidList.setCreationDate(Timestamp.from(Instant.now()));
        bidListRepository.save(bidList);
    }

    @Override
    @Transactional
    public void updateBidList(Integer id, BidListDto BidListDto) {
        BidList bidList = getSpecificBidListById(id);
        bidList.setAccount(BidListDto.getAccount());
        bidList.setType(BidListDto.getType());
        bidList.setBidQuantity(BidListDto.getBidQuantity());
        bidList.setRevisionDate(Timestamp.from(Instant.now()));
        bidListRepository.save(bidList);
    }

    @Override
    @Transactional
    public void deleteBidList(Integer id) {
        bidListRepository.deleteById(id);
    }
}
