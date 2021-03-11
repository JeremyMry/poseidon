package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.IBidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class BidListServiceImpl implements IBidListService {

    @Autowired
    BidListRepository bidListRepository;

    @Autowired
    Logger logger;

    @Override
    public BidList getSpecificBidListById(Integer id) {
        logger.info("BidList " + id + " find");
        return bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id: " + id));
    }

    @Override
    public List<BidList> getAllBidList() {
        logger.info("BidList List find");
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
        logger.info("BidList created");
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
        logger.info("BidList " + id + " updated");
        bidListRepository.save(bidList);
    }

    @Override
    @Transactional
    public void deleteBidList(Integer id) {
        logger.info("BidList " + id + " deleted");
        bidListRepository.deleteById(id);
    }
}
