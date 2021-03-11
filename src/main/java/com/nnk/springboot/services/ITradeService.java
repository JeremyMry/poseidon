package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDto;

import java.util.List;

public interface ITradeService {

    public Trade getSpecificTradeById(Integer id);

    public List<Trade> getAllTrade();

    public void createTrade(TradeDto tradeDto);

    public void updateTrade(Integer id, TradeDto tradeDto);

    public void deleteTrade(Integer id);
}
