package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDto;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.impl.TradeServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DirtiesContext(classMode=DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
public class TradeServiceTest {

    @Autowired
    TradeServiceImpl tradeService;

    @Autowired
    TradeRepository tradeRepository;

    @Test
    public void getSpecificTradeTest() {
        Trade trade = new Trade();
        trade.setAccount("a");
        trade.setType("ab");
        trade.setBuyQuantity(15.0);

        tradeRepository.save(trade);

        Assertions.assertNotNull(tradeService.getSpecificTradeById(1));
        Assertions.assertEquals(tradeService.getSpecificTradeById(1).getAccount(), trade.getAccount());
        Assertions.assertEquals(tradeService.getSpecificTradeById(1).getType(), trade.getType());
        Assertions.assertEquals(tradeService.getSpecificTradeById(1).getBuyQuantity(), trade.getBuyQuantity());
    }

    @Test
    public void getSpecificTradeThatDoesntExistTest() {
        Exception exception = Assert.assertThrows(IllegalArgumentException.class, () -> tradeService.getSpecificTradeById(1));

        String expectedMessage = "Invalid Trade Id: 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getAllTradesTest() {
        Trade trade = new Trade();
        trade.setAccount("a");
        trade.setType("ab");
        trade.setBuyQuantity(15.0);

        Trade trade1 = new Trade();
        trade1.setAccount("a");
        trade1.setType("ab");
        trade1.setBuyQuantity(15.0);


        tradeRepository.save(trade);
        tradeRepository.save(trade1);


        Assertions.assertEquals(tradeService.getAllTrade().size(), 2);
    }

    @Test
    public void getAllTradesWhenThereIsNone() {
        Assertions.assertEquals(tradeService.getAllTrade().size(), 0);
        Assertions.assertNotNull(tradeService.getAllTrade());
    }

    @Test
    public void createTradeTest() {
        TradeDto tradeDto = new TradeDto("a", "ab", 15.0);

        tradeService.createTrade(tradeDto);

        Assertions.assertEquals(tradeRepository.findById(1).get().getAccount(), tradeDto.getAccount());
        Assertions.assertEquals(tradeRepository.findById(1).get().getType(), tradeDto.getType());
        Assertions.assertEquals(tradeRepository.findById(1).get().getBuyQuantity(), tradeDto.getBuyQuantity());
        Assertions.assertNotNull(tradeRepository.findById(1).get().getCreationDate());
    }

    @Test
    public void updateTradeTest() {
        Trade trade = new Trade();
        trade.setAccount("a");
        trade.setType("ab");
        trade.setBuyQuantity(15.0);

        tradeRepository.save(trade);

        TradeDto tradeDto = new TradeDto("a", "ab", 15.0);
        tradeService.updateTrade(1, tradeDto);

        Assertions.assertEquals(tradeRepository.findById(1).get().getAccount(), "a");
        Assertions.assertEquals(tradeRepository.findById(1).get().getType(), "ab");
        Assertions.assertEquals(java.util.Optional.ofNullable(tradeRepository.findById(1).get().getBuyQuantity()), java.util.Optional.ofNullable(15.0));
        Assertions.assertNotNull(tradeRepository.findById(1).get().getRevisionDate());
        Assertions.assertEquals(tradeRepository.findAll().size(), 1);
    }

    @Test
    public void deleteTradeTest() {
        Trade trade = new Trade();
        trade.setAccount("a");
        trade.setType("ab");
        trade.setBuyQuantity(15.0);

        tradeRepository.save(trade);

       tradeService.deleteTrade(1);

        Assertions.assertFalse(tradeRepository.findById(1).isPresent());
    }
}
