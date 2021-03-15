package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.impl.BidListServiceImpl;
import com.nnk.springboot.services.impl.CustomUserDetailsService;
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

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser
@WebMvcTest(BidListController.class)
public class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListServiceImpl bidListService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void bidListGetAllBidListControllerTest() throws Exception {
        List<BidList> listBidList = new ArrayList<>();

        when(bidListService.getAllBidList()).thenReturn(listBidList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/bidList/list"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("bidList/list"))
                .andExpect(model().attributeExists("bidLists"));
    }

    @Test
    public void addBidListControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/bidList/add"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("bidList/add"))
                .andExpect(model().attributeExists("bidListDto"));
    }

    @Test
    public void validateBidListControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/bidList/validate").with(csrf()).contentType(MediaType.ALL)
                .param("account", "a").param("type", "a").param("bidQuantity", "15.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bidList/list"));
    }

    @Test
    public void validateBidListControllerWithErrorTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/bidList/validate").with(csrf()).contentType(MediaType.ALL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("bidList/add"))
                .andExpect(model().attributeExists("bidListDto"));
    }

    @Test
    public void showUpdateFormBidListControllerTest() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("a");
        bidList.setType("a");
        bidList.setBidQuantity(15.0);

        when(bidListService.getSpecificBidListById(Mockito.any(Integer.class))).thenReturn(bidList);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/bidList/update/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("bidList/update"))
                .andExpect(model().attributeExists("bidListDto"));
    }

    @Test
    public void updateBidControllerTest() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("a");
        bidList.setType("a");
        bidList.setBidQuantity(15.0);

        when(bidListService.getSpecificBidListById(Mockito.any(Integer.class))).thenReturn(bidList);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/bidList/update/1").with(csrf()).contentType(MediaType.ALL)
                .param("account", "b").param("type", "b").param("bidQuantity", "15.0"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bidList/list"));
    }

    @Test
    public void updateBidControllerWithErrorTest() throws Exception {
        BidList bidList = new BidList();
        bidList.setAccount("a");
        bidList.setType("a");
        bidList.setBidQuantity(15.0);

        when(bidListService.getSpecificBidListById(Mockito.any(Integer.class))).thenReturn(bidList);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/bidList/update/1").with(csrf()).contentType(MediaType.ALL))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("bidList/update"));
    }

    @Test
    public void deleteBidControllerTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/bidList/delete/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/bidList/list"));
    }
}
