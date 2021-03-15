package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.impl.CustomUserDetailsService;
import com.nnk.springboot.services.impl.TradeServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WithMockUser
@WebMvcTest(TradeController.class)
public class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeServiceImpl tradeService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void bidListGetAllTradesControllerTest() throws Exception {
        List<Trade> tradeList = new ArrayList<>();

        when(tradeService.getAllTrade()).thenReturn(tradeList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/trade/list"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("trade/list"))
                .andExpect(model().attributeExists("trades"));
    }

    @Test
    public void addTradeControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/trade/add"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("trade/add"))
                .andExpect(model().attributeExists("tradeDto"));
    }

    @Test
    public void validateTradeControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/trade/validate").with(csrf()).contentType(MediaType.ALL)
                .param("account", "a").param("type", "a").param("buyQuantity", "15.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/trade/list"));
    }

    @Test
    public void validateTradeControllerWithErrorTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/trade/validate").with(csrf()).contentType(MediaType.ALL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("trade/add"))
                .andExpect(model().attributeExists("tradeDto"));
    }

    @Test
    public void showUpdateFormTradeControllerTest() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("a");
        trade.setType("a");
        trade.setBuyQuantity(15.0);

        when(tradeService.getSpecificTradeById(Mockito.any(Integer.class))).thenReturn(trade);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/trade/update/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("trade/update"))
                .andExpect(model().attributeExists("tradeDto"));
    }

    @Test
    public void updateTradeControllerTest() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("a");
        trade.setType("a");
        trade.setBuyQuantity(15.0);

        when(tradeService.getSpecificTradeById(Mockito.any(Integer.class))).thenReturn(trade);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/trade/update/1").with(csrf()).contentType(MediaType.ALL)
                .param("account", "b").param("type", "b").param("buyQuantity", "15.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/trade/list"));
    }

    @Test
    public void updateTradeControllerWithErrorTest() throws Exception {
        Trade trade = new Trade();
        trade.setAccount("a");
        trade.setType("a");
        trade.setBuyQuantity(15.0);

        when(tradeService.getSpecificTradeById(Mockito.any(Integer.class))).thenReturn(trade);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/trade/update/1").with(csrf()).contentType(MediaType.ALL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("trade/update"));
    }

    @Test
    public void deleteTradeControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/trade/delete/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/trade/list"));
    }
}
