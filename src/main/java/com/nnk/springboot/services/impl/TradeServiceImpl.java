package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDto;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.ITradeService;
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

    @Override
    public Trade getSpecificTradeById(Integer id) {
        return tradeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Trade> getAllTrade() {
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
        tradeRepository.save(trade);
    }

    @Override
    @Transactional
    public void deleteTrade(Integer id) {
        tradeRepository.deleteById(id);
    }
}
