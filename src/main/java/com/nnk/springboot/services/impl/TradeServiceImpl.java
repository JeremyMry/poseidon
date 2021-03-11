package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDto;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.ITradeService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class TradeServiceImpl implements ITradeService {

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    Logger logger;

    @Override
    public Trade getSpecificTradeById(Integer id) {
        logger.info("Trade " + id + " find");
        return tradeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Trade Id: " + id));
    }

    @Override
    public List<Trade> getAllTrade() {
        logger.info("Trade List find");
        return tradeRepository.findAll();
    }

    @Override
    @Transactional
    public void createTrade(TradeDto tradeDto) {
        Trade trade = new Trade();
        trade.setAccount(tradeDto.getAccount());
        trade.setType(tradeDto.getType());
        trade.setBuyQuantity(tradeDto.getBuyQuantity());
        trade.setCreationDate(Timestamp.from(Instant.now()));
        logger.info("Trade created");
        tradeRepository.save(trade);
    }

    @Override
    @Transactional
    public void updateTrade(Integer id, TradeDto tradeDto) {
        Trade trade = getSpecificTradeById(id);
        trade.setAccount(tradeDto.getAccount());
        trade.setType(tradeDto.getType());
        trade.setBuyQuantity(tradeDto.getBuyQuantity());
        trade.setRevisionDate(Timestamp.from(Instant.now()));
        logger.info("Trade " + id + " updated");
        tradeRepository.save(trade);
    }

    @Override
    @Transactional
    public void deleteTrade(Integer id) {
        logger.info("Trade " + id + " deleted");
        tradeRepository.deleteById(id);
    }
}
